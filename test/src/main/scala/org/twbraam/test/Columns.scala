package org.twbraam.test

object Columns {
  // before OneHotCoded
  val zeroCols = List(26,34,36,37,38,47,48,59,61,62)
  val codeCols = List(27,28,30,31,40,53,57,63,64,72)
  val boolCols = List(46)
  val meanCols = List(3)

  val noneCols = List(6,9,25,30,31,32,33,35,57,58,60,63,64,72,73,74)
  val modeCols = List(42,2,23,24,53,78,55)


  // after OneHotCoded
  val notEncoded = Map(
    0 -> 0,
    1 -> 1,
    3 -> 2,
    4 -> 3,
    17 -> 4,
    18 -> 5,
    19 -> 6,
    20 -> 7,
    26 -> 8,
    27 -> 9,
    28 -> 10,
    30 -> 11,
    31 -> 12,
    34 -> 13,
    36 -> 14,
    37 -> 15,
    38 -> 16,
    40 -> 17,
    43 -> 18,
    44 -> 19,
    45 -> 20,
    46 -> 21,
    47 -> 22,
    48 -> 23,
    49 -> 24,
    50 -> 25,
    51 -> 26,
    52 -> 27,
    53 -> 28,
    54 -> 29,
    56 -> 30,
    57 -> 31,
    59 -> 32,
    61 -> 33,
    62 -> 34,
    63 -> 35,
    64 -> 36,
    66 -> 37,
    67 -> 38,
    68 -> 39,
    69 -> 40,
    70 -> 41,
    71 -> 42,
    72 -> 43,
    75 -> 44,
    76 -> 45,
    77 -> 46,
    80 -> 47
  )

  val encoded = Map(
    2 -> ("MSZoning", List(("MSZoning_C (all)", 48), ("MSZoning_FV", 49), ("MSZoning_RH", 50), ("MSZoning_RL", 51), ("MSZoning_RM", 52)).toMap),
    5 -> ("Street", List(("Street_Grvl", 53), ("Street_Pave", 54)).toMap),
    6 -> ("Alley", List(("Alley_Grvl", 55), ("Alley_None", 56), ("Alley_Pave", 57)).toMap),
    7 -> ("LotShape", List(("LotShape_IR1", 58), ("LotShape_IR2", 59), ("LotShape_IR3", 60), ("LotShape_Reg", 61)).toMap),
    8 -> ("LandContour", List(("LandContour_Bnk", 62), ("LandContour_HLS", 63), ("LandContour_Low", 64), ("LandContour_Lvl", 65)).toMap),
    9 -> ("Utilities", List(("Utilities_AllPub", 66), ("Utilities_NoSeWa", 67)).toMap),
    10 -> ("LotConfig", List(("LotConfig_Corner", 68), ("LotConfig_CulDSac", 69), ("LotConfig_FR2", 70), ("LotConfig_FR3", 71), ("LotConfig_Inside", 72)).toMap),
    11 -> ("LandSlope", List(("LandSlope_Gtl", 73), ("LandSlope_Mod", 74), ("LandSlope_Sev", 75)).toMap),
    12 -> ("Neighborhood", List(("Neighborhood_Blmngtn", 76), ("Neighborhood_Blueste", 77), ("Neighborhood_BrDale", 78), ("Neighborhood_BrkSide", 79), ("Neighborhood_ClearCr", 80), ("Neighborhood_CollgCr", 81), ("Neighborhood_Crawfor", 82), ("Neighborhood_Edwards", 83), ("Neighborhood_Gilbert", 84), ("Neighborhood_IDOTRR", 85), ("Neighborhood_MeadowV", 86), ("Neighborhood_Mitchel", 87), ("Neighborhood_NAmes", 88), ("Neighborhood_NPkVill", 89), ("Neighborhood_NWAmes", 90), ("Neighborhood_NoRidge", 91), ("Neighborhood_NridgHt", 92), ("Neighborhood_OldTown", 93), ("Neighborhood_SWISU", 94), ("Neighborhood_Sawyer", 95), ("Neighborhood_SawyerW", 96), ("Neighborhood_Somerst", 97), ("Neighborhood_StoneBr", 98), ("Neighborhood_Timber", 99), ("Neighborhood_Veenker", 100)).toMap),
    13 -> ("Condition1", List(("Condition1_Artery", 101), ("Condition1_Feedr", 102), ("Condition1_Norm", 103), ("Condition1_PosA", 104), ("Condition1_PosN", 105), ("Condition1_RRAe", 106), ("Condition1_RRAn", 107), ("Condition1_RRNe", 108), ("Condition1_RRNn", 109)).toMap),
    14 -> ("Condition2", List(("Condition2_Artery", 110), ("Condition2_Feedr", 111), ("Condition2_Norm", 112), ("Condition2_PosA", 113), ("Condition2_PosN", 114), ("Condition2_RRAe", 115), ("Condition2_RRAn", 116), ("Condition2_RRNn", 117)).toMap),
    15 -> ("BldgType", List(("BldgType_1Fam", 118), ("BldgType_2fmCon", 119), ("BldgType_Duplex", 120), ("BldgType_Twnhs", 121), ("BldgType_TwnhsE", 122)).toMap),
    16 -> ("HouseStyle", List(("HouseStyle_1.5Fin", 123), ("HouseStyle_1.5Unf", 124), ("HouseStyle_1Story", 125), ("HouseStyle_2.5Fin", 126), ("HouseStyle_2.5Unf", 127), ("HouseStyle_2Story", 128), ("HouseStyle_SFoyer", 129), ("HouseStyle_SLvl", 130)).toMap),
    21 -> ("RoofStyle", List(("RoofStyle_Flat", 131), ("RoofStyle_Gable", 132), ("RoofStyle_Gambrel", 133), ("RoofStyle_Hip", 134), ("RoofStyle_Mansard", 135), ("RoofStyle_Shed", 136)).toMap),
    22 -> ("RoofMatl", List(("RoofMatl_CompShg", 137), ("RoofMatl_Membran", 138), ("RoofMatl_Metal", 139), ("RoofMatl_Roll", 140), ("RoofMatl_Tar&Grv", 141), ("RoofMatl_WdShake", 142), ("RoofMatl_WdShngl", 143)).toMap),
    23 -> ("Exterior1st", List(("Exterior1st_AsbShng", 144), ("Exterior1st_AsphShn", 145), ("Exterior1st_BrkComm", 146), ("Exterior1st_BrkFace", 147), ("Exterior1st_CBlock", 148), ("Exterior1st_CemntBd", 149), ("Exterior1st_HdBoard", 150), ("Exterior1st_ImStucc", 151), ("Exterior1st_MetalSd", 152), ("Exterior1st_Plywood", 153), ("Exterior1st_Stone", 154), ("Exterior1st_Stucco", 155), ("Exterior1st_VinylSd", 156), ("Exterior1st_Wd Sdng", 157), ("Exterior1st_WdShing", 158)).toMap),
    24 -> ("Exterior2nd", List(("Exterior2nd_AsbShng", 159), ("Exterior2nd_AsphShn", 160), ("Exterior2nd_Brk Cmn", 161), ("Exterior2nd_BrkFace", 162), ("Exterior2nd_CBlock", 163), ("Exterior2nd_CmentBd", 164), ("Exterior2nd_HdBoard", 165), ("Exterior2nd_ImStucc", 166), ("Exterior2nd_MetalSd", 167), ("Exterior2nd_Other", 168), ("Exterior2nd_Plywood", 169), ("Exterior2nd_Stone", 170), ("Exterior2nd_Stucco", 171), ("Exterior2nd_VinylSd", 172), ("Exterior2nd_Wd Sdng", 173), ("Exterior2nd_Wd Shng", 174)).toMap),
    25 -> ("MasVnrType", List(("MasVnrType_BrkCmn", 175), ("MasVnrType_BrkFace", 176), ("MasVnrType_None", 177), ("MasVnrType_Stone", 178)).toMap),
    29 -> ("Foundation", List(("Foundation_BrkTil", 179), ("Foundation_CBlock", 180), ("Foundation_PConc", 181), ("Foundation_Slab", 182), ("Foundation_Stone", 183), ("Foundation_Wood", 184)).toMap),
    32 -> ("BsmtExposure", List(("BsmtExposure_Av", 185), ("BsmtExposure_Gd", 186), ("BsmtExposure_Mn", 187), ("BsmtExposure_No", 188), ("BsmtExposure_None", 189)).toMap),
    33 -> ("BsmtFinType1", List(("BsmtFinType1_ALQ", 190), ("BsmtFinType1_BLQ", 191), ("BsmtFinType1_GLQ", 192), ("BsmtFinType1_LwQ", 193), ("BsmtFinType1_None", 194), ("BsmtFinType1_Rec", 195), ("BsmtFinType1_Unf", 196)).toMap),
    35 -> ("BsmtFinType2", List(("BsmtFinType2_ALQ", 197), ("BsmtFinType2_BLQ", 198), ("BsmtFinType2_GLQ", 199), ("BsmtFinType2_LwQ", 200), ("BsmtFinType2_None", 201), ("BsmtFinType2_Rec", 202), ("BsmtFinType2_Unf", 203)).toMap),
    39 -> ("Heating", List(("HeatingQC", 17), ("Heating_Floor", 204), ("Heating_GasA", 205), ("Heating_GasW", 206), ("Heating_Grav", 207), ("Heating_OthW", 208), ("Heating_Wall", 209)).toMap),
    41 -> ("CentralAir", List(("CentralAir_N", 210), ("CentralAir_Y", 211)).toMap),
    42 -> ("Electrical", List(("Electrical_FuseA", 212), ("Electrical_FuseF", 213), ("Electrical_FuseP", 214), ("Electrical_Mix", 215), ("Electrical_SBrkr", 216)).toMap),
    55 -> ("Functional", List(("Functional_Maj1", 217), ("Functional_Maj2", 218), ("Functional_Min1", 219), ("Functional_Min2", 220), ("Functional_Mod", 221), ("Functional_Sev", 222), ("Functional_Typ", 223)).toMap),
    58 -> ("GarageType", List(("GarageType_2Types", 224), ("GarageType_Attchd", 225), ("GarageType_Basment", 226), ("GarageType_BuiltIn", 227), ("GarageType_CarPort", 228), ("GarageType_Detchd", 229), ("GarageType_None", 230)).toMap),
    60 -> ("GarageFinish", List(("GarageFinish_Fin", 231), ("GarageFinish_None", 232), ("GarageFinish_RFn", 233), ("GarageFinish_Unf", 234)).toMap),
    65 -> ("PavedDrive", List(("PavedDrive_N", 235), ("PavedDrive_P", 236), ("PavedDrive_Y", 237)).toMap),
    73 -> ("Fence", List(("Fence_GdPrv", 238), ("Fence_GdWo", 239), ("Fence_MnPrv", 240), ("Fence_MnWw", 241), ("Fence_None", 242)).toMap),
    74 -> ("MiscFeature", List(("MiscFeature_Gar2", 243), ("MiscFeature_None", 244), ("MiscFeature_Othr", 245), ("MiscFeature_Shed", 246), ("MiscFeature_TenC", 247)).toMap),
    78 -> ("SaleType", List(("SaleType_COD", 248), ("SaleType_CWD", 249), ("SaleType_Con", 250), ("SaleType_ConLD", 251), ("SaleType_ConLI", 252), ("SaleType_ConLw", 253), ("SaleType_New", 254), ("SaleType_Oth", 255), ("SaleType_WD", 256)).toMap),
    79 -> ("SaleCondition", List(("SaleCondition_Abnorml", 257), ("SaleCondition_AdjLand", 258), ("SaleCondition_Alloca", 259), ("SaleCondition_Family", 260), ("SaleCondition_Normal", 261), ("SaleCondition_Partial", 262)).toMap)
  )

}
