This project simulates an inventory report in a usual store. Only in this report, the coding uses some design patterns to maximize the efficiency of the report.

		Padrão Strategy - Definir uma família de algoritmos, encapsulá-los e fazer com que seja fácil trocar um deles por outro. 
				O padrão permite variar o algoritmo independentemente dos clientes que o usam. Capture a Abstração em uma 
				interface e esconda os detalhes de implementação nas classes derivadas. Nesse projeto, imagino que esses 
				algoritmos sejam aqueles de ordenação, critério de ordenação e filtragem.
				Será três famílias. A interface será o nome daquilo que eles fazem (exemplo, algOrd). Haverão interfaces
				de ordenação assim como de critério de ordenação, que será chamado na última parte da ordenação e fará 
				esse trabalho.  

		Padrão Decorator - Será aplicado nos formatos possíveis de visualização e formatação HTML. O Decorator é basicamente isso,
				um decorador, ou invólucro, para um objeto base. Ainda, podem haver invólucros que envolvem outros 
				invólucros. O Decorator será uma classe abstrata que será estendida por outras classes que representam uma 
				funcionalidade nova e diferente. Algo parecido com isso terá que funcionar:

				new FORMATO_ITALICO(new FORMATO_NEGRITO(new ProdutoPadrao(21, "Playstation 2", "Games", 10, 499.90)))


	O que você acabou de observar acima é uma espécie de explicação que eu escrevi para mim mesmo no começo deste trabalho.
Isso me ajudou à formular todas as classes que eram necessárias para implemetá-los. Toda essa refatoração e inclusão desses 
padrões consistiu em extinguir os métodos Ordena e particiona da classe principal, delegando isso para as classes periféricas,
ainda possibiltando ao usuário divesas formas de realzar a mesma tarefa e poder trocar de uma para outra facilmente. Com isso tudo,
o padrão Decorator possibilitou, na criação do produto, poderíamos escolher que tipo de formatação ele terá quando for printado.
No atual estado, a saída demonstra quase todas as novas funcionalidades.
