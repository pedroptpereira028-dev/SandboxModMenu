#!/bin/sh
set -e
if command -v gradle >/dev/null 2>&1; then
  exec gradle "$@"
fi
echo "Gradle não encontrado. No Codespaces, abra um novo terminal após o devcontainer instalar as ferramentas."
exit 1
