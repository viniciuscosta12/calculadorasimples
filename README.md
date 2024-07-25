# Calculadora Android em Kotlin

Este é um projeto de calculadora simples para Android, desenvolvido em Kotlin. A calculadora suporta operações básicas (soma, subtração, multiplicação e divisão), além de funções de radiciação e potência. O resultado da última operação pode ser utilizado como entrada para operações subsequentes.
O projeto faz parte da Atividade 1 da disciplina de Desenvolvimento Android.

## Funcionalidades
- **Operações Básicas**: Soma, subtração, multiplicação e divisão.
- **Radiciação**: Cálculo da raiz quadrada.
- **Potência**: Cálculo de potência.
- **Armazenamento de Resultado**: O resultado da última operação é armazenado e pode ser usado como entrada para operações subsequentes.
- **Deleção e Limpeza**: Opções para deletar o último caractere ou limpar o display.

## Pré-requisitos

- Android Studio
- Kotlin
- SDK Android 21 ou superior

## Estrutura do Projeto

### Layout

O layout da aplicação está definido no arquivo `activity_main.xml` e inclui:

- **Display**: Um `TextView` para mostrar números e resultados.
- **Botões**: Botões para números, operadores, funções e ações de controle (como deletar e limpar).

### Código Kotlin

A lógica da calculadora está implementada na classe `MainActivity`. As principais funcionalidades são:

- **onNumberClick**: Atualiza o display com números e pontos decimais.
- **onOperatorClick**: Processa operadores e calcula o resultado se necessário.
- **onSqrtClick**: Calcula a raiz quadrada do valor atual.
- **onEqualClick**: Calcula o resultado da operação e atualiza o display.
- **onDeleteClick**: Remove o último caractere do display.
- **onClearClick**: Limpa o display e reseta o estado da calculadora.
