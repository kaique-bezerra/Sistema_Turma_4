package CasasBahia;

import java.util.ArrayList;
import java.util.Scanner;

import CasasBahia.excecoes.CodigoInvalidoExption;
import CasasBahia.excecoes.NomeInvalidoExption;
import CasasBahia.excecoes.PrecoInvalidoException;
import CasasBahia.excecoes.VoltagemInvalidoException;

public class Sistema {

    private ArrayList<Produto> produtos;

    public Sistema() {
        this.produtos = new ArrayList<Produto>();
    }

    public void adicionarProduto(Scanner scanner) throws PrecoInvalidoException,VoltagemInvalidoException,CodigoInvalidoExption,NomeInvalidoExption{
        System.out.println("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        if (nome == null || nome.equals("") ) {
            throw new NomeInvalidoExption("nem um nome digitado");
        }
        System.out.println("Digite o codigo: ");
        String codigo = scanner.nextLine();

        if(codigo == null || codigo.equals("")){
            throw new CodigoInvalidoExption("nem um codigo digitado");
        }
        System.out.println("Digite o preço do produto: ");
        Double preco = scanner.nextDouble();
        scanner.nextLine();



        if(preco <= 0) {
            throw new PrecoInvalidoException("O preço precisa ser maior que 0");
        }


        System.out.println("Qual o tipo do produto: ");
        System.out.println("1 - Movel ");
        System.out.println("2 - Eletro ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            adicionarMovel(scanner, nome, codigo, preco);
        } else if (opcao == 2) {
            adicionarEletro(scanner, nome, codigo, preco);
        }
    }

    private void adicionarMovel(Scanner scanner, String nome, String codigo, double preco)  {
        System.out.println("Digite o material:");
        String material = scanner.nextLine();

        System.out.println("Digite a categoria:");
        String categoria = scanner.nextLine();

        Movel movel = new Movel(codigo, nome, preco, categoria, material);
        produtos.add(movel);

        System.out.println("Produto adicionado");

    }

    private void adicionarEletro(Scanner scanner, String nome, String codigo, double preco)throws VoltagemInvalidoException {
        CategoriaEletro categoriaEletro = null;
        System.out.println("Qual a categoria do eletrodomestico cadastrado?");
        System.out.println("1 - Cozinha");
        System.out.println("2 - Quarto");
        System.out.println("3 - Lavanderia");

        int opcaoCategoria = scanner.nextInt();
        scanner.nextLine();
        if (opcaoCategoria == 1) {
            categoriaEletro = CategoriaEletro.COZINHA;
        } else if (opcaoCategoria == 2) {
            categoriaEletro = CategoriaEletro.QUARTO;
        } else if (opcaoCategoria == 3) {
            categoriaEletro = CategoriaEletro.LAVANDERIA;
        }
        System.out.println("Digite a voltagem");
        int voltagem = scanner.nextInt();
        scanner.nextLine();
        if (voltagem != 110  && voltagem != 220){
            throw new VoltagemInvalidoException("digite uma voltagem valida!!!");
        }
        Eletrodomestico eletro = new Eletrodomestico(codigo, nome, preco, categoriaEletro, voltagem);
        produtos.add(eletro);
        System.out.println("Produto adicionado");

    }

    public void listarProdutos() {
        if (produtos.size() == 0) {
            System.out.println("Nenhum produto cadastrado!");
        } else {
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }

    }


    public Produto buscarProduto(Scanner scanner) throws CodigoInvalidoExption{
        System.out.println("Digite o codigo procurado:");
        String codigoProcurado = scanner.nextLine();
        if (codigoProcurado ==null||codigoProcurado.equals("")){
            throw new CodigoInvalidoExption("nem um codigo digitado");
        }

        for (Produto produto : produtos) {
            if(produto.getCodigo().equals(codigoProcurado)) {
                System.out.println("Produto encontrado!");
                System.out.println(produto);
                return produto;
            }
        }
        System.out.println("Produto não encontrado!");
        return null;
    }

    public void removerProduto(Scanner scanner) {
        System.out.println("Digite o codigo do produto: ");
        String codigoProcurado = scanner.nextLine();
        for(int i=0; i < produtos.size(); i++) {
            if(produtos.get(i).getCodigo().equals(codigoProcurado)) {
                produtos.remove(i);
                System.out.println("Produto removido");
                return;
            }
        }
        System.out.println("Produto nâo encontrado!");
    }
}

