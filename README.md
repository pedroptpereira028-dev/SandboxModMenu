# Sandbox Mod Menu

Projeto Android independente para testes de interface e recursos fictícios.

## Abrir no GitHub Codespaces

1. Suba este repositório para o GitHub.
2. Abra o repositório.
3. Clique em **Code → Codespaces → Create codespace on main**.
4. No terminal do Codespace, execute:

```bash
./gradlew assembleDebug
```

O APK será gerado em:

```text
app/build/outputs/apk/debug/app-debug.apk
```

Para verificar o projeto:

```bash
./gradlew test
```

## Observação

Este aplicativo é um sandbox independente. Ele não injeta código, não lê memória e não modifica FC Mobile ou qualquer outro jogo/aplicativo.
