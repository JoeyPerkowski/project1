FROM ubuntu:latest
LABEL authors="joeyp"

ENTRYPOINT ["top", "-b"]