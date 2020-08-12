# Evolving Stream Model

Continous learning model that might be implemented in applications where a model should constantly adapt to the latest features.

## Example architecture

![Example architecture](https://raw.githubusercontent.com/twbraam/evolvingstreammodel/master/media/evolvingStreamModel.png)

## Project contents

This projects contains:

#### predictor

Flink Job that ingests two streams:
- feature stream
- new model signal

The feature stream is constantly ingested into the latest 
model available. If a new model signal is received, it will be 
broadcasted to all processors, which will then fetch the latest
version and immediately apply it.

#### train

A simple XGBoost project to illustrate the POC. The project probably makes
more sense for an algorithm that does not need labeled data.

