echo "Starting the docker container"
cd docker && docker-compose up -d
sleep 5s
echo "Running tests"
cd .. && python test/test.py --port 5000 --cert-path localcert/localhost.crt