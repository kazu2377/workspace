# FROM ubuntu:22.04

# ARG USER_NAME
# ARG USER_GROUP_NAME
# ARG USER_UID
# ARG USER_GID


# # general packages
# ARG PKG="sudo git vim curl zip unzip locales ${TMP_PKG}"

# SHELL ["/bin/bash", "-c"]

# RUN apt-get update \
#     && apt-get install -y ${PKG} \
#     && groupadd --gid ${USER_GID} ${USER_GROUP_NAME} \
#     && useradd --uid ${USER_UID} --shell /bin/bash --gid ${USER_GID} -m ${USER_NAME} \
#     && echo %${USER_GROUP_NAME} ALL=\(ALL\) NOPASSWD:ALL > /etc/sudoers.d/${USER_GROUP_NAME} \
#     && chmod 0440 /etc/sudoers.d/${USER_GROUP_NAME}

# # enable git
# RUN su ${USER_NAME} --command \
#     'sudo git config --system --add safe.directory "/home/${USER_NAME}/workspace"'

# # ---------------------------------- sdkman ----------------------------------
# ARG JAVA_VERSION="17.0.4-amzn"
# ARG GRADLE_VERSION="7.5"

# RUN su ${USER_NAME} --command \
#     'curl -s "https://get.sdkman.io" | bash \
#     && source "${HOME}/.sdkman/bin/sdkman-init.sh" \
#     && sdk install java "${JAVA_VERSION}" \
#     && sdk install gradle ${GRADLE_VERSION}'

FROM ubuntu:22.04

# 引数の設定
ARG USER_NAME=vscode
ARG USER_GROUP_NAME=workspace
ARG USER_UID=1000
ARG USER_GID=1000

# パッケージの設定
ARG PKG="sudo git vim curl zip unzip locales"

# シェルの設定
SHELL ["/bin/bash", "-c"]

# 必要なパッケージのインストールとユーザー作成
RUN apt-get update \
    && apt-get install -y ${PKG} \
    && groupadd --gid ${USER_GID} ${USER_GROUP_NAME} \
    && useradd --uid ${USER_UID} --shell /bin/bash --gid ${USER_GID} -m ${USER_NAME} \
    && echo "%${USER_GROUP_NAME} ALL=(ALL) NOPASSWD:ALL" > /etc/sudoers.d/${USER_GROUP_NAME} \
    && chmod 0440 /etc/sudoers.d/${USER_GROUP_NAME}

# gitの設定
RUN sudo -u ${USER_NAME} git config --global --add safe.directory "/home/${USER_NAME}/workspace"

# SDKMANのインストールとJava/Gradleの設定
USER ${USER_NAME}
ENV SDKMAN_DIR="/home/${USER_NAME}/.sdkman"
RUN curl -s "https://get.sdkman.io" | bash \
    && source "${SDKMAN_DIR}/bin/sdkman-init.sh" \
    && sdk install java "${JAVA_VERSION}" \
    && sdk install gradle ${GRADLE_VERSION} \
    && sdk flush archives

# 作業ディレクトリの設定
WORKDIR /home/${USER_NAME}/workspace

# コンテナが起動する際のデフォルトのシェルを指定
CMD [ "bash" ]
