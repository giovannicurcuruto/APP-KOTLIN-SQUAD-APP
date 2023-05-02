[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
## Recrutamento para Desenvolvedor Mobile Jr - Android

- [Recrutamento para Desenvolvedor Mobile Jr - Android](#recrutamento-para-desenvolvedor-mobile-jr---android)
- [Descrição da narrativa](#descrição-da-narrativa)
  - [Requisitos mandatórios do projeto](#requisitos-mandatórios-do-projeto)
  - [Requisitos opcionais do projeto](#requisitos-opcionais-do-projeto)
- [DiagramasDiagramas e respostas do Backend](#diagramas-e-respostas-do-backend)
- [Design](#design)
- [Compilar e executar o projeto](#compilar-e-executar-o-projeto)
- [Tecnologias/Padrões utilizados](#tecnologiaspadrões-utilizados)
- [Contatos](#contatos)
- [Licença](#licença)

## Descrição do Problema (Fornecido previamente)

Você deve desenvolver um aplicativo nativo em Android (Kotlin), utilizando nossa API interna, ela foi concebida para se assemelhar com o que o desenvolvedor já integrado em nosso time trabalha, para isso a aplicação deverá interagir com nosso banco de dados via requisições, onde pode ser possível ler, adicionar, excluir e atualizar dados de um dispositivo como também listar os dispositivos salvos no servidor para apresenta-los ao usuário da aplicação, além disso haverá a possibilidade de salva-lo como favorito.

O Protótipo conta com a primeira tela sendo a dashboard, a qual apresenta na parte superior um componente de pesquisa de dispositivo, pesquisando pelo nome do dispositivo, logo abaixo serão mostrados cards para cada dispositivo, e na parte inferior uma bottom menu que permite escolher o filtro de que dispositivos mostrar e também um float action buttom (FAB), a qual permite a adição de novos dispositivos.

Cada card de dispositivo terá no canto esquerdo um ícone representando o tipo de dispositivo (alarme ou vídeo), depois o nome do dispositivo e ao fim o ícone kebab que permite abrir o menu suspenso deste card. O menu flutuante possui quatro itens de acesso: “Editar o dispositivo”, “Informações”, “Marcar Favorito” / “Desmarcar Favorito” e “Remover Dispositivo”.

Já a bottom menu além do FAB terá quatro ícones, sendo o primeiro a qual irá listar todos os dispositivos adicionados, o segundo apenas os dispositivos que foram marcados como favoritos, já o terceiro e quarto serão um filtro dos dispositivos por tipo. Tela “Adicionar dispositivo” 
/ “Editar dispositivo”, são telas exatamente iguais em seus componentes, sua diferença é que no primeiro o usuário irá precisar digitar os dados para salvar no servidor, no segundo ele já possui 
os dados, mas pode edita-los/atualiza-los.

Já a tela informações, irá listar alguns dos dados dos dispositivos

### Requisitos mandatórios do projeto

- [x] Lista de dispositivo (Dashboard);
- [ ] Tela de Informações;
- [x] Bottom Menu (Layout) com itens funcionando (Home, bookmark, alarm, video, FAB);
- [x] Desing System Material UI;
- [x] Ler os dispositivos e informações;
- [x] Utilização do BitBucket, GitHub ou GitLab(Versionamento de código);


### Requisitos opcionais do projeto

- [ ] Tela de Adição
- [ ] Tela de edição
- [ ] Salvar favorito/Desmarcar Favorito
- [x] Pesquisa
- [ ] CRUD
- [x] Tratar com Response ao usuário com Toast 


## Diagramas e respostas do Backend

Para o desenvolvimento do case, foi disponiblizado via Swagger (Open API), um link mostrando como utilizar o CRUD da aplicação via backend. 

Para visualizar esteo Swagger, poderá ser acessado [aqui](http://squadapps.ddns-intelbras.com.br:3000/api).

Como meio de autenticação na aplicação, foi utilizado e implementado no código a utilização de um Tokken de acesso, pois era uma aplicação interna e uma api de uso interno.  


## Design

Para o desenvolvimento do case foi disponibilizado o visual de algumas telas, tal qual, algumas cores, icones (Assets). Como é um documento no Adobe XD fechado, não acho viavel o compartilhamento deste design, contudo segue abaixo como fica a aplicação rodando na minha versão. 

![1](https://user-images.githubusercontent.com/11502844/235806104-2bd80e27-3c7d-4585-a81a-044976b240d5.png)

![2](https://user-images.githubusercontent.com/11502844/235806112-ee7cf6f3-30b3-4ae6-8501-ef78d204d76f.png)


## Compilar e executar o projeto

1. Clonar o repositório com o comanbo abaixo:

```bash
  git clone https://github.com/giovannicurcuruto/APP-KOTLIN-SQUAD-APP.git
```

2. Alterar para branch ``Master```

3. Abrir através do Android Studio para utilização com o Emulador. Recomendo antes de tentar executar realizar um Build da aplicação.

Foi utilizado o pré-set do Emulador abaixo: 

````
Pixel 4 (API 30 - Android 11.0)
````

## Tecnologias/Padrões utilizados

- [x] MVVM
- [x] Kotlin (usando com Coroutines)
- [x] Hilt - Injection Dependence
- [x] Retrofit
- [x] OkHTTP
- [x] Gson
- [x] Git


## Contatos
Giovanni Salvatore de Almeida Curcuruto

<a href = "mailto:ggcurcuruto@gmail.com"><img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"  height="25"></a>
<a href="https://www.linkedin.com/in/giovanni-curcuruto-b6689596" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"  height="25"></a>   
</div>    
