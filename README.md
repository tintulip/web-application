# Application Infra

This repo contains the terraform code to build the infrastructure for the scenario 1 workload. 
 
## Test
```bash
cd test

AWS_REGION=eu-west-2 AWS_PROFILE=tintulip-sandbox-admin go test -timeout 90m
```
## Actions in workflows

Currently we are running two jobs in parrallel in the CI/CD:
- `Terraform checks`
- [Terratest](github.com/gruntwork-io/terratest/modules/terraform)


Below are the security terraform checks that run in the pipeline.

- [Tfsec](https://github.com/tfsec/tfsec)
- [Checkov](https://github.com/bridgecrewio/checkov)

## Terraform tooling

Currently do not have terraform wrappers that efficiently run terraform across multiple environment and on multiple components. Created a temporary makefile till the tooling becomes available. 


