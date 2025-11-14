
// CodeSavanna || Under Construction || Ignore All Stupid Code

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void estatisticasHabitat() throws IOException{

        File file = new File("./files/animais.csv");

        Scanner sc = new Scanner(file);

        if(sc.hasNextLine()){
            sc.nextLine();
        }

        String [] allHabitatsArray = new String[40];
        String [] uniqueHabitatsArray = new String[40];
        int index = 0;
        int newIndex = 0;

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String [] array = line.split(";");

            allHabitatsArray[index] = array[3];
            index++;
        }

        for (int i = 0; i < index; i++) {

            boolean exists = false;

            for (int j = 0; j < newIndex; j++) {
                if(allHabitatsArray[i].equals(uniqueHabitatsArray[j])){
                    exists = true;
                    break;
                }
            }

            if(!exists){
                uniqueHabitatsArray[newIndex] = allHabitatsArray[i];
                newIndex++;
            }
        }

        File file1 = new File("./files/animais.csv");

        int otherIndex = 0;

        int [] uniqueHabitatCounter = new int[40];

        while (otherIndex < newIndex){
            Scanner sc1 = new Scanner(file1);

            if(sc1.hasNextLine()){
                sc1.nextLine();
            }

            int totalzinho = 0;

            while(sc1.hasNextLine()){
                String line = sc1.nextLine();
                String [] arrayCounter = line.split(";");

                if(uniqueHabitatsArray[otherIndex].equals(arrayCounter[3])){
                    totalzinho++;
                }
            }

            uniqueHabitatCounter[otherIndex] = totalzinho;
            otherIndex++;
        }

//        File file2 = new File("./files/animais.csv");
//
//        Scanner sc3 = new Scanner(file2);
//
//        int [] uniqueInteractionCounter = new int[40];
//
//        if(sc3.hasNextLine()){
//            sc3.nextLine();
//        }
//
//        String [] arrozinho = new String[40];
//        int superIndex = 0;
//
//        while(sc3.hasNextLine()){
//            String linhosa = sc3.nextLine();
//            String [] arroz = linhosa.split(";");
//
//            if(uniqueHabitatsArray[superIndex].equals(arroz[3])){
//
//                arrozinho[superIndex] = arroz[0];
//
//                superIndex++;
//
//                File file3 = new File("./files/interacoes.csv");
//
//                Scanner sc4 = new Scanner(file3);
//
//                if(sc4.hasNextLine()){
//                    sc4.nextLine();
//                }
//                for (int h = 0; h < newIndex; h++) {
//                    int interacoesCounter = 0;
//                    for (int i = 0; i < arrozinho.length; i++){
//
//                        if(!(arrozinho[i] == null)){
//                            while(sc4.hasNextLine()){
//                                String linheira = sc4.nextLine();
//                                String [] arrasou = linheira.split(";");
//
//                                if(arrozinho[i].equals(arrasou[3])){
//                                    interacoesCounter++;
//                                }
//                            }
//                        }
//                    }
//                    uniqueInteractionCounter[superIndex - 1] = interacoesCounter;
//                }
//            }
//        }

        for (int i = 0; i < otherIndex; i++) {
            System.out.println("Habitat: " + uniqueHabitatsArray[i]);
            System.out.println("Animais No Habitat: " + uniqueHabitatCounter[i]);
//            System.out.println("Interacoes: " + uniqueInteractionCounter[i]);
        }

    }

    public static void rankingExtincao() throws IOException{

        File file = new File("./files/animais.csv");

        Scanner sc = new Scanner(file);

        String [] array = new String[25];
        String [] arrayOfIds = new String[25];
        int [] arrayOfInterCount = new int[25];
        double [] arrayOfMoneyCount = new double[25];
        String [] arrayOfRespectiveID = new String[25];
        String [] arrayOfNomeAnimal = new String[25];
        int index = 0;

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String [] arrayzaco = line.split(";");

            // array com ids e com o estado extinçao SIM, mesmos indices
            if(arrayzaco[5].equals("SIM")){
                array[index] = arrayzaco[5];
                arrayOfIds[index] = arrayzaco[0];
                index++;


                File file1 = new File("./files/interacoes.csv");

                Scanner scInter = new Scanner(file1);

                int totalInter = 0;
                double totalMoney = 0.0;

                while(scInter.hasNextLine()){
                    String linha = scInter.nextLine();
                    String [] inter = linha.split(";");

                    if(arrayOfIds[index - 1].equals(inter[3])){
                        totalInter++;
                        totalMoney += Double.parseDouble(inter[5]);
                    }
                }
                arrayOfInterCount[index - 1] = totalInter;
                arrayOfMoneyCount[index - 1] = totalMoney;
                arrayOfRespectiveID[index - 1] = arrayOfIds[index - 1];
                arrayOfNomeAnimal[index - 1] = arrayzaco[1] + " " + arrayzaco[2];
            }
        }

        for (int i = 0; i < arrayOfInterCount.length - 1; i++) {
            for (int j = 0; j < arrayOfInterCount.length - 1; j++) {
                if(arrayOfInterCount[j]>arrayOfInterCount[j+1]){
                    int temp = arrayOfInterCount[j];
                    arrayOfInterCount[j] = arrayOfInterCount[j+1];
                    arrayOfInterCount[j+1] = temp;

                    double tempo = arrayOfMoneyCount[j];
                    arrayOfMoneyCount[j] = arrayOfMoneyCount[j+1];
                    arrayOfMoneyCount[j+1] = tempo;

                    String tempinho = arrayOfRespectiveID[j];
                    arrayOfRespectiveID[j] = arrayOfRespectiveID[j+1];
                    arrayOfRespectiveID[j+1] = tempinho;

                    String tempoleca = arrayOfNomeAnimal[j];
                    arrayOfNomeAnimal[j] = arrayOfNomeAnimal[j+1];
                    arrayOfNomeAnimal[j+1] = tempoleca;
                }
            }
        }

        int rankCounter = 1;

        System.out.println("======================= RANKING DE ESPECIES QUASE NO FIM =======================");
        for (int i = arrayOfInterCount.length - 1; i >= 0; i--) {
            if(!(arrayOfNomeAnimal[i] == null)){
                System.out.println("\n============================================================");
                System.out.println("RANKING " + rankCounter);
                System.out.println("Nome Animal: " + arrayOfNomeAnimal[i]);
                System.out.println("ID: " + arrayOfRespectiveID[i]);
                System.out.println("Interações: " + arrayOfInterCount[i]);
                System.out.println("Valor Pago: " + arrayOfMoneyCount[i]);
                System.out.println("============================================================");
                rankCounter++;
            }
        }
        System.out.println("=======================================================================================");
    }

    public static void espetaculoMaisRentavel() throws IOException{

        File file = new File("./files/interacoes.csv");

        Scanner sc = new Scanner(file);

        String [] newArray = new String[50];
        String [] newArrayValues = new String[50];

        int index = 0;

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String [] array = line.split(";");

            if(array[2].equalsIgnoreCase("ESPETACULO")){
                newArray[index] = array[4];
                newArrayValues[index] = array[5];
                index++;
            }
        }

        double mostProfitable = 0.0;
        String best = "";

        for (int i = 0; i < index; i++) {

            double total = 0;

            for (int j = 0; j < index; j++){
                if(newArray[i].equalsIgnoreCase(newArray[j])){
                    total += Double.parseDouble(newArrayValues[j]);
                }
            }

            if(total > mostProfitable){
                mostProfitable = total;
                best = newArray[i];
            }
        }

        System.out.println("\n============================= MAIS RENTAVEL =============================\n");
        System.out.println(mostProfitable + " moneys");
        System.out.println(best);
        System.out.println("\n=========================================================================");
    }

    public static void listarPadrinhos() throws IOException{

        File file = new File("./files/interacoes.csv");

        Scanner sc = new Scanner(file);
        Scanner input = new Scanner(System.in);

        boolean exists = false;
        int padrinhosCounter = 0;
        String clientID = "";
        Double valorPago = 0.;
        String eventName = "";
        String clientName = "";
        String clientEmail = "";

        System.out.print("Introduza o ID do animal: ");
        String id = input.nextLine();

        while (sc.hasNextLine()){
            String linha = sc.nextLine();
            String [] array = linha.split(";");

            if(array[3].equals(id) && array[2].equals("APADRINHAMENTO")){
                padrinhosCounter++;
                clientID = array[1];
                eventName = array[4];
                exists = true;
                valorPago = Double.parseDouble(array[5]);

                File fileOther = new File("./files/clientes.csv");

                Scanner in = new Scanner(fileOther);

                while(in.hasNextLine()){
                    String linhaClient = in.nextLine();
                    String [] arrayClient = linhaClient.split(";");

                    if(arrayClient[0].equals(clientID)){
                        clientName = arrayClient[1];
                        clientEmail = arrayClient[3];
                    }
                }

                System.out.println("--------------------------------------------");
                System.out.println("Nome do cliente: " + clientID + " (" + clientEmail + ")");
                System.out.println("Valor mensal pago: " + valorPago);
                System.out.println("Plano de Apadrinhamento: " + eventName);
                System.out.println("--------------------------------------------");
            }
        }

        if(!exists){
            System.out.println("O animal não existe.");
        }
    }

//    public static void topTresApadrinhamento() throws IOException{
//
//    }

//    public static void maisPopular() throws IOException{
//
//        File file = new File("./files/interacoes.csv");
//
//        Scanner scArraySize = new Scanner(file);
//
//        int counterAnimal = 0;
//
//        while(scArraySize.hasNextLine()){
//            scArraySize.nextLine();
//            counterAnimal++;
//        }
//
//        String [] idAnimais = new String[counterAnimal];
//
//        Scanner scFillArray = new Scanner(file);
//
//        System.out.println("==================== IDs Gerais ====================");
//
//        int index = 0;
//
//        while(scFillArray.hasNextLine()){
//            String linha = scFillArray.nextLine();
//            String [] array = linha.split(";");
//
//            for (int i = 0; i < idAnimais.length; i++){
//                if(!(array[3].equalsIgnoreCase(idAnimais[i]))){
//                    idAnimais[i] = array[3];
//                }
//            }
//
//            for (int i = 0; i < 1; i++){
//                System.out.println(idAnimais[i]);
//            }
//        }
//
//        System.out.println("====================================================");
//
//        String [] idUniqueAnimais = new String[counterAnimal];
//
//        idUniqueAnimais[0] = idAnimais[0];
//
//        for (int i = 0; i < idAnimais.length; i++){
//            for (int j = 0; j < idUniqueAnimais.length; j++) {
//                if(!(idAnimais[i].equalsIgnoreCase(idUniqueAnimais[j]))){
//                    idUniqueAnimais[i] = idAnimais[i];
//                }
//            }
//        }
//
//        System.out.println("==================== IDs Unicos ====================");
//
//        for (int i = 0; i < idUniqueAnimais.length; i++){
//            System.out.println(idUniqueAnimais[i]);
//        }
//
//        System.out.println("====================================================");
//
//    }

    public static void receitaTotal() throws IOException{

        File file = new File("./files/interacoes.csv");

        Scanner sc = new Scanner(file);

        if (sc.hasNextLine()){
            sc.nextLine();
        }

        double totalMoneyCounter = 0;
        double totalMoneyVisita = 0;
        double totalMoneyEspetaculo = 0;
        double totalMoneyAlimentacao = 0;
        double totalMoneyApadrinhamento = 0;

        while (sc.hasNextLine()) {

            String linha = sc.nextLine();
            String [] array = linha.split(";");

            if(array[2].equals("VISITA")){
                totalMoneyVisita += Double.parseDouble(array[5]);
            }

            if(array[2].equals("ESPETACULO")){
                totalMoneyEspetaculo += Double.parseDouble(array[5]);
            }

            if(array[2].equals("ALIMENTACAO")){
                totalMoneyAlimentacao += Double.parseDouble(array[5]);
            }

            if(array[2].equals("APADRINHAMENTO")){
                totalMoneyApadrinhamento += Double.parseDouble(array[5]);
            }

            totalMoneyCounter += Double.parseDouble(array[5]);
        }

        System.out.println("\n============================= RECEITA TOTAL =============================");
        System.out.println("Total De Interações: " + totalMoneyCounter + "€");
        System.out.println("Visitas money: " + totalMoneyVisita + "€");
        System.out.println("Espetáculos money: " + totalMoneyEspetaculo + "€");
        System.out.println("Alimentação money: " + totalMoneyAlimentacao + "€");
        System.out.println("Apadrinhamento money: " + totalMoneyApadrinhamento + "€");
        System.out.println("===================================================================");

    }

    public static void estatisticasGerais() throws IOException{

        File file = new File("./files/interacoes.csv");

        Scanner sc = new Scanner(file);

        if (sc.hasNextLine()){
            sc.nextLine();
        }

        int totalCounter = 0;
        int totalVisita = 0;
        int totalEspetaculo = 0;
        int totalAlimentacao = 0;
        int totalApadrinhamento = 0;

        while (sc.hasNextLine()) {

            String linha = sc.nextLine();
            String [] array = linha.split(";");

            if(array[2].equals("VISITA")){
                totalVisita++;
            }

            if(array[2].equals("ESPETACULO")){
                totalEspetaculo++;
            }

            if(array[2].equals("ALIMENTACAO")){
                totalAlimentacao++;
            }

            if(array[2].equals("APADRINHAMENTO")){
                totalApadrinhamento++;
            }

            totalCounter++;
        }

        System.out.println("\n============================= ESTATISTICAS GERAIS =============================");
        System.out.println("Total De Interações: " + totalCounter);
        System.out.println("Visitas: " + totalVisita);
        System.out.println("Espetáculos: " + totalEspetaculo);
        System.out.println("Alimentação: " + totalAlimentacao);
        System.out.println("Apadrinhamento: " + totalApadrinhamento);
        System.out.println("===================================================================");

    }

    public static void conteudoAnimais() throws IOException {

        File file = new File("./files/animais.csv");

        Scanner sc = new Scanner(file);

        System.out.println("============================= LISTA DE ANIMAIS =============================");
        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String [] array = linha.split(";");
            System.out.println("| " + array[0] + " | " + array[1] + " | " + array[2] + " | " + array[3] + " | " + array[4] + " | " + array[5] + " |");

        }
        System.out.println("===================================================================");
    }

    public static void conteudoClientes() throws IOException {

        File file = new File("./files/clientes.csv");

        Scanner sc = new Scanner(file);

        System.out.println("============================= LISTA DE CLIENTES =============================");
        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String [] array = linha.split(";");
            System.out.println("| " + array[0] + " | " + array[1] + " | " + array[2] + " | " + array[3] + " |");

        }
        System.out.println("===================================================================");
    }

    public static void conteudoInteracoes() throws IOException {

        File file = new File("./files/interacoes.csv");

        Scanner sc = new Scanner(file);

        System.out.println("============================= LISTA DE INTERACOES =============================");
        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String [] array = linha.split(";");
            System.out.println("| " + array[0] + " | " + array[1] + " | " + array[2] + " | " + array[3] + " | " + array[4] + " | " + array[5] + " | " + array[6] + " |");

        }
        System.out.println("===================================================================");
    }

    public static void menuCliente() {

        Scanner input = new Scanner(System.in);

        int opcao;

        do {

            System.out.println("\n\n==================== MENU CLIENTE CODESAVANNA ====================");
            System.out.println("1. Ver Catálogo De Animais Por Habitat");
            System.out.println("2. Ver Atividades De Um Animal");
            System.out.println("3. Simular Apadrinhamento De Um Animal");
            System.out.println("4. Jogo Adivinha A Especie");
            System.out.println("0. Voltar");

            System.out.print("Opção: ");
            opcao = input.nextInt();

            switch (opcao) {

                case 1:
                    // catalogoAnimais();
                    break;

                case 2:
                    // atividadesAnimal();
                    break;

                case 3:
                    // simularApadrinhamento();
                    break;

                case 4:
                    // jogoEspecie();
                    break;

                case 0: // Voltar
                    break;

                default:
                    System.out.println("\nOpção Inválida");
                    break;
            }

        } while (opcao != 0);

    }

    public static void menuAdmin() throws IOException{

        Scanner input = new Scanner(System.in);

        int opcao;

        do {

            System.out.println("\n\n==================== MENU ADMIN CODESAVANNA ====================");
            System.out.println("1. Listar Conteúdo Dos Ficheiros");
            System.out.println("2. Estatísticas Gerais De Interações");
            System.out.println("3. Receita Total Por Tipo De Interação");
            System.out.println("4. Animal Mais Popular");
            System.out.println("5. Top 3 Espécies Com Mais Apadrinhamentos");
            System.out.println("6. Listar Padrinhos De Um Animal");
            System.out.println("7. Espetáculo Mais Rentável");
            System.out.println("8. Ranking De Animais Em Perigo De Extinção");
            System.out.println("9. Estatísticas Por Habitat");
            System.out.println("0. Voltar");

            System.out.print("Opção: ");
            opcao = input.nextInt();

            switch (opcao) {

                case 1:

                    int opcaoConteudo;

                    do{
                        System.out.println("\n\n==================== LISTAR CONTEÚDO ====================");
                        System.out.println("1. Animais");
                        System.out.println("2. Clientes");
                        System.out.println("3. Interações");
                        System.out.println("0. Voltar");

                        System.out.print("Opção: ");
                        opcaoConteudo = input.nextInt();

                        switch(opcaoConteudo){

                            case 1:
                                conteudoAnimais();
                                break;
                            case 2:
                                conteudoClientes();
                                break;
                            case 3:
                                conteudoInteracoes();
                                break;

                        }
                    } while (opcaoConteudo != 0);
                    break;

                case 2:
                    estatisticasGerais();
                    break;
                case 3:
                    receitaTotal();
                    break;
                case 4:
                    // maisPopular();
                    break;
                case 5:
                    // topTresApadrinhamento();
                    break;
                case 6:
                    listarPadrinhos();
                    break;
                case 7:
                    espetaculoMaisRentavel();
                    break;
                case 8:
                    rankingExtincao();
                    break;
                case 9:
                    estatisticasHabitat();
                    break;
                default:
                    System.out.println("\nOpção Inválida");
                    break;
            }

        } while (opcao != 0);
    }

    public static void menuLogin() throws IOException{

        Scanner input = new Scanner(System.in);

        int opcaoLogin;
        String username, password;

        do {

            System.out.println("\n==================== MENU LOGIN ====================");
            System.out.println("1. ADMIN");
            System.out.println("2. CLIENTE");
            System.out.println("0. SAIR");

            System.out.print("\nTipo de Utilizador: ");
            opcaoLogin = input.nextInt();

            switch (opcaoLogin) {

                case 1:

                    System.out.print("\nUsername: ");
                    username = input.next();

                    System.out.print("Password: ");
                    password = input.next();

                    if (username.equals("admin") && password.equals("admin")) {
                        menuAdmin();
                    } else {
                        System.out.println("Login incorreto");
                    }

                    break;

                case 2:
                    menuCliente();
                    break;

                case 0:
                    System.out.println("\nObrigado! Volte sempre...");
                    break;

                default:
                    System.out.println("\nOpção Inválida");
                    break;
            }

        } while (opcaoLogin != 0);
    }

    public static void main(String[] args) throws IOException{
        menuLogin();
    }
}