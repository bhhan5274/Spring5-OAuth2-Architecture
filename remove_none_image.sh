#!/bin/bash

set -e

docker rmi -f $(docker images -f "dangling=true" -q)
