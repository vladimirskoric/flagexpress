#!/bin/sh
api_url="${API_URL:-https://flagexpress-api-stage.azurewebsites.net}"
debug="${DEBUG:-true}"
cat <<EOF
(function(window) {
  window["env"] = window["env"] || {};

  // Environment variables
  window["env"]["apiUrl"] = '$api_url';
  window["env"]["debug"] = '$debug';
})(this);
EOF
