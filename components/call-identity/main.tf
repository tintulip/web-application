data "aws_caller_identity" "current" {}

provider "aws" {
  region = "eu-west-2"
}