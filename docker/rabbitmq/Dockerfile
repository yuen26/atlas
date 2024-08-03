FROM rabbitmq:3.13-management-alpine

RUN apk update && \
    apk add --no-cache curl

# Download and install additional plugins
RUN curl -L https://github.com/rabbitmq/rabbitmq-delayed-message-exchange/releases/download/v3.13.0/rabbitmq_delayed_message_exchange-3.13.0.ez > $RABBITMQ_HOME/plugins/rabbitmq_delayed_message_exchange-3.13.0.ez
RUN rabbitmq-plugins enable --offline rabbitmq_delayed_message_exchange
