variable "regionDefault" {
  default = "us-east-1"
}

variable "labRole" {
  default = "arn:aws:iam::729256766334:role/LabRole"
}

variable "projectName" {
  default = "techchallenge"
}

variable "subnetA" {
  default = "subnet-08aa091e641814f16"
}

variable "subnetB" {
  default = "subnet-07a65ddaafe2d1e1f"
}

variable "subnetC" {
  default = "subnet-035d9cbe14aeb0fa2"
}

variable "vpcId" {
  default = "vpc-0282105466f35db1d"
}

variable "instanceType" {
  default = "t3a.medium"
}

variable "principalArn" {
  default = "arn:aws:iam::729256766334:role/voclabs"
}

variable "policyArn" {
  default = "arn:aws:eks::aws:cluster-access-policy/AmazonEKSClusterAdminPolicy"
}

variable "accessConfig" {
  default = "API_AND_CONFIG_MAP"
}

variable "AWS_ACCESS_KEY_ID" {

}

variable "AWS_SECRET_ACCESS_KEY" {

}

variable "AWS_SESSION_TOKEN" {

}
