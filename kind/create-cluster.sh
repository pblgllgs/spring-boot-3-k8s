#!/bin/sh

echo "Initializing k8s cluster with kind"

kind create cluster --config kind-config.yml

echo "\n-------------------------------------------------\n"

echo "Instalin NGINX Ingres"

kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml

echo "\n-------------------------------------------------\n"

echo "Waiting got NGINX Inggress to be ready..."

sleep 10

kubectl wait --namespace ingress-nginx \
  --for=condition=ready pod \
  --selector=app.kubernetes.io/component=controller \
  --timeout=180s

echo "\n"

