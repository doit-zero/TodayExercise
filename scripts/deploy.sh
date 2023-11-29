# EC2에서 실행되는 파일
#!/bin/bash

export $(cat /home/ec2-user/.env | xargs)

BUILD_JAR=$(ls /home/ec2-user/action/build/libs/*.jar)
JAR_NAME=$(basename $BUILD_JAR)
DEPLOY_PATH=/home/ec2-user/action/

echo "> build 파일명: $JAR_NAME" >> $DEPLOY_PATH/deploy.log

echo "> build 파일 복사" >> $DEPLOY_PATH/deploy.log
cp $BUILD_JAR $DEPLOY_PATH

echo "> 현재 실행중인 애플리케이션 pid 확인" >> $DEPLOY_PATH/deploy.log
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> $DEPLOY_PATH/deploy.log
else
  echo "> 기존 애플리케이션 종료: kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME
echo "> DEPLOY_JAR 배포" >> $DEPLOY_PATH/deploy.log
nohup java -jar $DEPLOY_JAR >> $DEPLOY_PATH/deploy.log 2>$DEPLOY_PATH/deploy_err.log &

# Check if the application is running
sleep 15
CHECK_PID=$(pgrep -f $JAR_NAME)
if [ -n "$CHECK_PID" ]
then
  echo "> 애플리케이션이 성공적으로 실행되었습니다. (PID: $CHECK_PID)" >> $DEPLOY_PATH/deploy.log
else
  echo "> 애플리케이션 실행에 실패했습니다. 로그를 확인하세요." >> $DEPLOY_PATH/deploy.log
fi
