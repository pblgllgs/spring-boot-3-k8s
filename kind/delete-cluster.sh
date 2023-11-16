#!/bin/sh
echo "Initializing k8s cluster with kind"
kind delete cluster --name k8s-cluster