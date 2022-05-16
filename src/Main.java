import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidFormatException, IOException {
        Graph graph = new Graph();
        enterData(graph);
        menu(graph);
        System.out.println("From: " + graph.getStart().getCity() + " To: " + graph.getGoal().getCity());
        System.out.println();

    }

    public static void menu(Graph graph) {

        Scanner scanner = new Scanner(System.in);


        boolean temp = true;
        System.out.println("Enter start City:");
        String start = scanner.nextLine();
        Node city = graph.getCitByName(start);
        while (temp) {
            if (graph.getCitByName(start) == null) {
                System.out.println("Start city does not exist, please choose another city:");
                System.out.println("Enter start City:");
                start = scanner.nextLine();
                city = graph.getCitByName(start);
                continue;
            }
            temp = false;
        }
        graph.setStart(city);

        temp = true;
        while (temp) {
            System.out.println("Enter goal City:");
            String goal = scanner.nextLine();
            graph.setGoal(city.getCitByName(goal));
            if (goal.equals(start)) {
                System.out.println("Goal and Start could not be the same, please choose another city:");
                continue;
            } else if (city.getCitByName(goal) == null) {
                System.out.println("Goal city does not exist, please choose another city:");
                continue;
            }

            temp = false;

        }


        System.out.println("----------WELCOME TO OUR SEARCH PROGRAM---------");

        System.out.println("Please Choose an Algorithm: ");
        System.out.println(" 1- Breadth First Search \n 2- Depth First Search \n 3- Iteratve Depining Search \n 4- Greedy Algorithm \n 5- A* with Airial Heuristic \n 6- A* with walk Heuristic \n 7- Exit");


        int Algo = scanner.nextInt();
        while (Algo > 0 && Algo < 7) {

            if (Algo == 1) {
                graph.BFS();
            }
            if (Algo == 2) {
                graph.DFS();
            }
            if (Algo == 3) {
                graph.IterativeDFS();
            }
            if (Algo == 4) {
                System.out.println("Please Choose a Route: ");
                System.out.println(" 1- Walking \n 2- Car \n 3- aerial \n else- Back");

                int r = scanner.nextInt();
                if (r > 3 || r < 1) {
                    continue;
                } else {

                    graph.Greedy(r);
                }

            }
            if (Algo == 5) {
                graph.aStar();
            }
            if (Algo == 6) {
                graph.aStar2();
            }
            if (Algo == 7) {
                return;
            }


            System.out.println("\nPlease Choose an Algorithm: ");
            System.out.println(" 1- Breadth First Search \n 2- Depth First Search \n 3- Iteratve Depining Search \n 4- Greedy Algorithm \n 5- A* with Airial Heuristic \n 6- A* with walk Heuristic \n 7- Exit");
            Algo = scanner.nextInt();
        }


    }

    public static void enterData(Graph graph) throws IOException, InvalidFormatException {

        //obtaining input bytes from a file
        OPCPackage fis = OPCPackage.open(new File("DB_Cities.xlsx"));
        //creating workbook instance that refers to .xls file
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        //creating a Sheet object to retrieve the object
        XSSFSheet sheet = wb.getSheetAt(0);
        //evaluating cell type
        Row row = sheet.getRow(0);
        for (int i = 2; i < 100000000; i++) {
            if (row.getCell(i) == null) {
                break;
            }
            Node n = new Node();
            n.setCity(row.getCell(i).getStringCellValue());
            graph.addToCities(n);
        }

        for (int r = 1; r < 100000; r++) {
            if (sheet.getRow(r) == null) {
                break;
            }
            for (int c = 2; c < 1000000; c++) {
                if (sheet.getRow(r).getCell(c) == null)
                    break;
                if (r + 1 != c) {
                    String s = sheet.getRow(r).getCell(c).toString().replaceAll("km", "").replaceAll("\\s", "");
                    String[] parts = s.split(",");
                    Node city = graph.getCitByName(sheet.getRow(0).getCell(c).toString());

                    SubNode node = new SubNode();
                    node.setCity(sheet.getRow(r).getCell(1).toString());
                    if (parts.length == 3) {
                        node.setAirialDistance(Integer.parseInt(parts[0]));
                        node.setCarDistance(Integer.parseInt(parts[1]));
                        node.setWalkDistance(Integer.parseInt(parts[2]));
                    } else {
                        node.setAirialDistance(Integer.parseInt(parts[0]));
                        node.setWalkDistance(Integer.parseInt(parts[1]));
                    }
                    city.addToConnectedCities(node);
                }

            }
        }

    }

}
