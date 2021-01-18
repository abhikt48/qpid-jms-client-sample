#Sample project of QPID JMS client

#Steps to execute

1. Modify parameters in Constants.java class
   * PUBLISHER_QUEUE_NAME = "**"
   * RECEIVER_QUEUE_NAME = "****"
   * SBUS_NAME = "***";  // Only Service BUS name without extension ".servicebus.windows.net"
   * USERNAME = "***"
   * PASSWORD = "****"
2. Run Publisher.java as **Java application** to publish message
3. Run Receiver.java as **Java application** to receive single message
