FROM python:latest

MAINTAINER binaryNomad

RUN mkdir /automation

COPY ./ssqaapitest /automation/ssqaapitest
COPY ./ssqaapitest/setup.py /automation/ssqaapitest

# Install any necessary dependencies
RUN pip install --upgrade pip setuptools wheel

WORKDIR /automation/
RUN python3 ssqaapitest/setup.py install