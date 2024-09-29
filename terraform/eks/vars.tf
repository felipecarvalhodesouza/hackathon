variable "regionDefault" {
  default = "us-east-1"
}

variable "labRole" {
  default = "arn:aws:iam::900551411373:role/LabRole"
}

variable "projectName" {
  default = "techchallenge"
}

variable "subnetA" {
  default = "subnet-0374755baa9cebff6"
}

variable "subnetB" {
  default = "subnet-0d72e0f0d4341e82e"
}

variable "subnetC" {
  default = "subnet-0d934e88a532a316e"
}

variable "vpcId" {
  default = "vpc-023f1dd6630df4f1b"
}

variable "instanceType" {
  default = "t3a.medium"
}

variable "principalArn" {
  default = "arn:aws:iam::900551411373:role/voclabs"
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
