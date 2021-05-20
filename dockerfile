FROM alpine:3.11.6

ENV alp_group c3-group
ENV alp_group_id 1500

ENV alp_user c3-user
ENV alp_user_id 1500
ENV alp_user_home /home/${alp_user}

ENV api_dir ${alp_user_home}/c3cam
ENV api_config_dir ${api_dir}/config
ENV app_name c3cam-gateway.jar

RUN apk update

# Install tzdata package to set Madrid time zone
RUN apk add --no-cache tzdata
RUN cp /usr/share/zoneinfo/Europe/Madrid /etc/localtime
RUN echo "Europe/Madrid" > /etc/timezone
RUN apk del --no-cache tzdata

# Create system group
RUN addgroup -S ${alp_group} -g ${alp_group_id}
# Create system user who belongs to the right above created group
RUN adduser -S ${alp_user} -u ${alp_user_id} -G ${alp_group}

# Add JRE 11
RUN apk add --no-cache openjdk11-jre

RUN apk upgrade

RUN mkdir ${api_dir}

COPY target/c3cam-poc*.jar ${api_dir}/${app_name}

WORKDIR ${alp_user_home}

USER ${alp_user_id}

ENTRYPOINT [ "/bin/sh", "-c", "java -jar ${api_dir}/${app_name}" ]


