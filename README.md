
![Mobile-Android-com-IA-3674](https://github.com/git-jr/3674-Machine-Learning-Android-Reconhecimento-Imagens/assets/35709152/268b6b25-2e5d-4ce9-91aa-a2add9391dba)

![](https://img.shields.io/github/license/alura-cursos/android-com-kotlin-personalizando-ui)


# Galeria-IA
O app Galeria-IA permite visualizar fotos e imagens do dispositivo do usuário com um visual moderno e intuitivo.
Usando o Google ML Kit, essas imagens serão classificadas automaticamente com base em seu conteúdo tanto usando modelos padrão quanto o que nos mesmos vamos treinar.
Ele foi feito em Jetpack Compose para explorar os recursos de aprendizado de máquina (Machine Learning) e Inteligência Artificial no Android com Kotlin. Através dele, iremos explorar.


## 🔨 Funcionalidades do projeto
O projeto base conta com as seguintes telas:
- Aba inicial: Escolha entre visualizar todas as imagens de uma pasta ou visualizar uma única imagem.
- Visualizar uma única imagem: Selecione qualquer imagem do dispositivo para analisar com o ML Kit.
- Galeria geral: Exibe uma grade com todas as imagens da pasta selecionada.
- Galeria com IA: Exibe uma grade com os grupos gerados automaticamente pela análise das imagens da pasta.
- Detalhes da imagem: Ao clicar em uma imagem da grade, ela é expandida e exibe abaixo os rótulos que foram identificados nela.



https://github.com/git-jr/3674-Machine-Learning-Android-Reconhecimento-Imagens/assets/35709152/a413f06d-a8f4-4fbb-9cd4-cff9bbc2f9fa



Ao final do curso, nosso projeto será capaz de:

- Realizar uma análise localmente das imagens escolhidas pelo usuário com a biblioteca de rotulagem de imagens do ML Kit.
- Apresentar em tela as informações extraídas das imagens.
- Usar os rótulos obtidos através das análises para criar grupos de imagens como "Pessoas", "Animais", "Comidas", "Música", etc.
- Fazer uso dos modelos personalizados que iremos treinar ou que você pode baixar para realizar análises personalizadas para casos de uso específicos.



## ✔️ Técnicas e tecnologias utilizadas

As técnicas e tecnologias utilizadas pra isso são:

- `Jetpack Compose`: kit de ferramentas moderno para criar IUs em dispositivos móveis
- `Kotlin`: linguagem de programação
- `Gradle Version Catalogs`: nova forma de gerenciar plugins e dependências em projetos Android
- `Material Design 3`: padrão de design recomendado pela google para criação de UI modernas
- `Hilt`: injeção de dependências
- `Navigating with Compose`: navegação entre composables e telas
- `Viewmodel e states`: gerenciamento de estados de tela
- `Kotlin Datetime`: navegação entre composables e telas.  
- `ML Kit Image labeling `: biblioteca para detectar e extrair informações sobre entidades em uma imagem em um amplo grupo de categorias. Além disso, permite a execução de modelos personalizados.
- `TensorFlow Lite Models`: conjunto de modelos pré-treinados e otimizados para executar em dispositivos móveis de forma eficiente.

- `Teachable Machine`: Plataforma que permite treinar modelos de aprendizado de máquina com facilidade, sem a necessidade de escrever código. Permite aos usuários criar modelos personalizados para reconhecimento de objetos, classificação de imagens e muito mais, com uma interface intuitiva e amigável.



## 📁 Acesso ao projeto

- Versão inicial: Veja o [código fonte][codigo-inicial] ou [baixe o projeto][download-inicial]
- Versão final: Veja o [código fonte][codigo-final] ou [baixe o projeto][download-final]

## 🛠️ Abrir e rodar o projeto
Após baixar o projeto, você pode abri-lo com o Android Studio. Para isso, na tela de launcher clique em:

“Open” (ou alguma opção similar), procure o local onde o projeto está e o selecione (caso o projeto seja baixado via zip, é necessário extraí-lo antes de procurá-lo). Por fim, clique em “OK” o Android Studio deve executar algumas tasks do Gradle para configurar o projeto, aguarde até finalizar. Ao finalizar as tasks, você pode executar o App 🏆


## 📚 Mais informações do curso

Gostou do projeto e quer conhecer mais? Você pode [acessar o curso](https://www.alura.com.br/curso-online-android-ia-google-ml-kit-traducao-textos) que desenvolve o projeto desde o começo!

[codigo-inicial]: https://github.com/git-jr/3674-Machine-Learning-Android-Reconhecimento-Imagens
[download-inicial]: https://github.com/git-jr/3674-Machine-Learning-Android-Reconhecimento-Imagens/archive/refs/heads/projeto-base.zip

[codigo-final]: https://github.com/git-jr/3674-Machine-Learning-Android-Reconhecimento-Imagens/tree/aula05
[download-final]: https://github.com/git-jr/3674-Machine-Learning-Android-Reconhecimento-Imagens/archive/refs/heads/aula05.zip


