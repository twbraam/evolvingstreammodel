import numpy as np
import pandas as pd

from sklearn.model_selection import cross_val_score

import xgboost as xgb
from hyperopt import hp, tpe, fmin


pd.set_option('display.max_columns', None)


if __name__ == '__main__':
    traindf = pd.read_csv("data/train.csv")

    for col in ('Alley', 'Utilities', 'MasVnrType', 'BsmtQual', 'BsmtCond', 'BsmtExposure', 'BsmtFinType1',
                'BsmtFinType2', 'FireplaceQu', 'GarageType', 'GarageFinish', 'GarageQual', 'GarageCond',
                'PoolQC', 'Fence', 'MiscFeature'):
        traindf[col] = traindf[col].fillna('None')

    for col in ('Electrical', 'MSZoning', 'Exterior1st', 'Exterior2nd', 'KitchenQual', 'SaleType', 'Functional'):
        traindf[col] = traindf[col].fillna(traindf[col].mode()[0])

    for col in ('MasVnrArea', 'BsmtFinSF1', 'BsmtFinSF2', 'BsmtUnfSF', 'TotalBsmtSF', 'BsmtFullBath', 'BsmtHalfBath',
                'GarageYrBlt', 'GarageCars', 'GarageArea'):
        traindf[col] = traindf[col].fillna(0)

    traindf['LotFrontage'] = traindf['LotFrontage'].fillna(traindf['LotFrontage'].mean())
    traindf = traindf[traindf['GrLivArea'] < 4000]
    len_traindf = traindf.shape[0]

    # turning some ordered categorical variables into ordered numerical
    # maybe this information about order can help on performance
    for col in ["ExterQual", "ExterCond", "BsmtQual", "BsmtCond", "HeatingQC", "KitchenQual",
                "FireplaceQu", "GarageQual", "GarageCond", "PoolQC"]:
        traindf[col] = traindf[col].map({"Gd": 4, "TA": 3, "Ex": 5, "Fa": 2, "Po": 1})

    traindf = pd.get_dummies(traindf)
    traindf.to_csv("preprocessed.csv", index=False)
    # x/y split

    xtrain = traindf.drop('SalePrice', axis=1)
    ytrain = traindf['SalePrice']

    space = {'n_estimators': hp.quniform('n_estimators', 1000, 4000, 100),
             'gamma': hp.uniform('gamma', 0.01, 0.05),
             'learning_rate': hp.uniform('learning_rate', 0.00001, 0.025),
             'max_depth': hp.quniform('max_depth', 3, 7, 1),
             'subsample': hp.uniform('subsample', 0.60, 0.95),
             'colsample_bytree': hp.uniform('colsample_bytree', 0.60, 0.98),
             'colsample_bylevel': hp.uniform('colsample_bylevel', 0.60, 0.98),
             'reg_lambda': hp.uniform('reg_lambda', 1, 20)
             }

    def objective(params):
        params = {'n_estimators': int(params['n_estimators']),
                  'gamma': params['gamma'],
                  'learning_rate': params['learning_rate'],
                  'max_depth': int(params['max_depth']),
                  'subsample': params['subsample'],
                  'colsample_bytree': params['colsample_bytree'],
                  'colsample_bylevel': params['colsample_bylevel'],
                  'reg_lambda': params['reg_lambda']}

        xb_a = xgb.XGBRegressor(**params)
        score = cross_val_score(xb_a, xtrain, ytrain, scoring='neg_mean_squared_error', cv=5, n_jobs=-1).mean()
        return -score

    best = fmin(fn=objective, space=space, max_evals=20, rstate=np.random.RandomState(1), algo=tpe.suggest)

    xb_b = xgb.XGBRegressor(random_state=0,
                            n_estimators=int(best['n_estimators']),
                            colsample_bytree=best['colsample_bytree'],
                            gamma=best['gamma'],
                            learning_rate=best['learning_rate'],
                            max_depth=int(best['max_depth']),
                            subsample=best['subsample'],
                            colsample_bylevel=best['colsample_bylevel'],
                            reg_lambda=best['reg_lambda']
                            )

    xb_b.fit(xtrain, ytrain)
    xb_b.save_model('model.json')
    output = xb_b.predict(xtrain)

    print(output[100])


