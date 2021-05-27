.ONESHELL:
.SHELL := /usr/bin/bash
.PHONY: plan apply init
TF_DIR="./components"

set-env:
	@if [ -z $(AWS_REGION) ]; then \
		echo "AWS_REGION has not been set"; \
		ERROR=1; \
	fi; \
	if [ -z $(AWS_PROFILE) ] && [ -z $(AWS_ACCESS_KEY_ID) ]; then \
		echo "Profile access keys have not been set"; \
		ERROR=1; \
	fi; \
	if [ ! -z $${ERROR} ] && [ $${ERROR} -eq 1 ]; then \
		exit 1; \
	fi;

sec-check:
	@ls $(TF_DIR) | xargs -I {} checkov -d="$(TF_DIR)/{}";

init: set-env
	@ls $(TF_DIR) | xargs -I {} terraform -chdir="$(TF_DIR)/{}" init;

plan: init
	@ls $(TF_DIR) | xargs -I {} terraform -chdir="$(TF_DIR)/{}" plan;

apply: init
	@ls $(TF_DIR) | xargs -I {} terraform -chdir="$(TF_DIR)/{}" apply;