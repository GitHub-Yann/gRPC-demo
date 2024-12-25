proto文件及一些公共代码 在grpc-common目录中
执行 mvn compile 生产代码
执行 mvn clean package install -Dmaven.test.skip=true 将common包部署到本机仓库
执行 mvn deploy -Dmaven.test.skip=true 将common包部署到机房私仓
