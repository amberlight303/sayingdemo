[![Build Status](https://www.travis-ci.com/amberlight303/sayingdemo.svg?branch=master)](https://www.travis-ci.com/amberlight303/sayingdemo)
# Wise Sayings 
___

### Description

It's a simple Kubernetes cloud app that consists of two microservices: `saying-generator` and `saying-provider`.

The `saying-generator` takes and returns randomly one out of 660 sayings. The `saying-provider` makes requests to the 
`saying-generator` and returns a saying to the client. The `saying-provider` is an entry point for the app, it's 
accessible from out of the k8s cluster via the `30001` port.

Inside the cluster, `saying-generator` listens the 9091 port, `saying-provider` - 9090. They communicate with each 
other using these ports. The `saying-generator` isn't accessible from the outside.

All sayings are in `sayings.json` as a file in resources inside `.jar`. The `saying-generator` gives a bare saying as 
a string, and the `saying-provider` wraps it in `A wise man once said: "<saying>".`.

### Deployment

In order to run it in minikube, run:

```
kubectl -- create -f saying-generator-deployment.yaml
kubectl -- create -f saying-provider-deployment.yaml
```

K8s will pull docker images automatically from Docker Hub. The `saying-provider` is accessible via the `30001` port.

The following command will open the service automatically in the default browser:
```
minikube service saying-provider
```
### Service

<p align="center">
    <img src="https://user-images.githubusercontent.com/26651009/111917769-6c2de880-8a8a-11eb-8aec-764729b938d9.png"/>
</p>