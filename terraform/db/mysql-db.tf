resource "aws_db_instance" "hackathon" {
  identifier           = var.projectName
  allocated_storage    = 20
  storage_type         = "gp2"
  engine               = "mysql"
  engine_version       = "8.0"
  instance_class       = "db.t3.micro"
  username             = var.DATABASE_USER
  password             = var.DATABASE_PASSWORD
  parameter_group_name = "default.mysql8.0"

  publicly_accessible  = true 

  # Adiciona o grupo de segurança
  vpc_security_group_ids = [aws_security_group.hackathon_security_group.id]
}

resource "aws_security_group" "hackathon_security_group" {
  name        = "hackathon_security_group"
  description = "Permite trafego de entrada para o banco de dados RDS"

  ingress {
    from_port   = 3306
    to_port     = 3306
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]  
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}
