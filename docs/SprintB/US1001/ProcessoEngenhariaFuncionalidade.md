# US1001: Specify a new product for sale
=======================================

# 1. Requirements

**US1001**: As Sales Clerk, I want to specify a new product for sale.

### Client clarifications 

Question 1: Regarding the barcode of a given product what should the coding standard be?

A1: Any of the supported standards. It might be set by configuration at system implantation time.

Question 2: Still regarding the barcode should the identifier be sequential, if not what method should we use to generate the identifier?

A2. Barcodes are manually introduced by users since each product barcode is provided by the product manufacture.

Question 3: It was mentioned in a previous question that both the production code and the internal code have a set regular expression, could an example of said regular expressions be given?

A3: For example, 4 letters followed by a dot (".") and ending with 5 digits.

Question 4:Can the regular expression be changed by an employee, if so which one?

A4: No! Such regular expressions are set by configuration at system implantation time.

Question 5:Can the Product be a part of a Super Category and a Category or will the Product be a part of a Category and that Category is a part of the Super Category? 

A5: "By simplicity, a category consists only of an alphanumeric code, and a description. Each product belongs mandatorily to a single category." As so, and contrary to what is suggested in your question, there is no hierarchy between categories.

Question 6:How many Categories should the product belong to?

A6: "Each product belong mandatorily to a single category." from the overview (2.1) of the project.

Question 7:In a product registration the attributes "photo", "internal code", "short description", "extended description", "technical description", "brand", "reference", "production code" are mandatory and how big they are?

A7: - photo: it might be of any common format (e.g. png, jpeg, svg);

    - short description: not empty having 30 chars maximum;

    - extended description: not empty with a minimum of 20 chars and 100 chars maximum;

    - technical description: multiple lines of text, preferably with no limit or the biggest possible.

    - brand: not empty having 50 chars maximum;

    - reference: not empty alphanumeric code with at 23 chars maximum;

    - production code: not empty alphanumeric code with at 23 chars maximum however it might meet a given regular expression defined/configured at deployment time.

    - internal code: not empty alphanumeric code with at 23 chars maximum however it might meet a given regular expression defined/configured at deployment time.

 
Mandatory attributes: internal code, short and extended description.
In accordance with the specification document, other atributes might also be mandatory.

Question 8:In document "brand name and reference." - Reference refers to the brand reference or product reference?

A8: The complete sentence is "Products basic information comprehends a unique internal code, a short and an extended description as well as a more technical description, a set of photos, a brand name and reference.".

The intent was to express that a product has:

    -a brand name (e.g.: "Samsung", "Nokia", "Bic");

    -the product reference set by the brand, which is an alphanumeric code (max. 23 chars).



# 2. Analysis

*Neste secção a equipa deve relatar o estudo/análise/comparação que fez com o intuito de tomar as melhores opções de design para a funcionalidade bem como aplicar diagramas/artefactos de análise adequados.*

*Recomenda-se que organize este conteúdo por subsecções.*

# 3. Design

*Nesta secção a equipa deve descrever o design adotado para satisfazer a funcionalidade. Entre outros, a equipa deve apresentar diagrama(s) de realização da funcionalidade, diagrama(s) de classes, identificação de padrões aplicados e quais foram os principais testes especificados para validar a funcionalidade.*

*Para além das secções sugeridas, podem ser incluídas outras.*

## 3.1. Realização da Funcionalidade

*Nesta secção deve apresentar e descrever o fluxo/sequência que permite realizar a funcionalidade.*

## 3.2. Diagrama de Classes

*Nesta secção deve apresentar e descrever as principais classes envolvidas na realização da funcionalidade.*

## 3.3. Padrões Aplicados

*Nesta secção deve apresentar e explicar quais e como foram os padrões de design aplicados e as melhores práticas.*

## 3.4. Tests 
*Nesta secção deve sistematizar como os testes foram concebidos para permitir uma correta aferição da satisfação dos requisitos.*

**Teste 1:** Verificar que não é possível criar uma instância da classe Exemplo com valores nulos.

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Exemplo instance = new Exemplo(null, null);
	}

# 4. Implementação

*Nesta secção a equipa deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;*

*Recomenda-se que organize este conteúdo por subsecções.*

# 5. Integração/Demonstração

*Nesta secção a equipa deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema.*

# 6. Observações

*Nesta secção sugere-se que a equipa apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados.*



