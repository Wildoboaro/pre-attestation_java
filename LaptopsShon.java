import java.util.*;
import java.util.stream.Collectors;

class Laptop {
    int size;
    String vendor;
    int ramOnBoard;
    String driveType;
    int driveSize;
    String installedOS;

    public Laptop(int size, String vendor, int ramOnBoard, String driveType, int driveSize, String installedOS) {
        this.size = size;
        this.vendor = vendor;
        this.ramOnBoard = ramOnBoard;
        this.driveType = driveType;
        this.driveSize = driveSize;
        this.installedOS = installedOS;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "Диагональ ='" + size + '\'' +
                ", производитель ='" + vendor + '\'' +
                ", объем оперативной памяти =" + ramOnBoard +
                ", тип накопителя =" + driveType +
                ", объем накопителя =" + driveSize +
                ", os='" + installedOS + '\'' +

                '}';
    }
}

public class LaptopsShon {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop(15, "HP", 16, "SSD", 512, "Windows 11"));
        laptops.add(new Laptop(12, "Apple", 8, "SSD", 256, "MacOS 14 Sonoma"));
        laptops.add(new Laptop(17, "Aser", 32, "HDD", 512, "Не установлена"));
        laptops.add(new Laptop(15, "Asus", 16, "HDD", 1024, "CentOs 7"));

        Map<String, Object> filters = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Правметры для выборки(фильтр):");
        System.out.println("1 - диагональ");
        System.out.println("2 - производитель");
        System.out.println("3 - объем установленной оперативной памяти");
        System.out.println("4 - тип установленного накопителя");
        System.out.println("5 - объем установленного накопителя");
        System.out.println("4 - тип установленного накопителя");        
        System.out.println("0 - Завершить выбор");

        int choice;
        while (true) {
            choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                
                case 1:
                    System.out.print("Введиет желаемую диагональ(12 -- 17):");
                    filters.put("size", scanner.nextInt());
                    System.out.println("Выберите следующий параметр для фильтрации или нажмите «0» для вывода результата.");
                    break;
                case 2:
                    System.out.print("Ввидите наименование желаемого производителя(HP, Apple, Aser, Asus):");
                    filters.put("vendor", scanner.next());
                    System.out.println("Выберите следующий параметр для фильтрации или нажмите «0» для вывода результата.");
                    break;
                case 3:
                    System.out.print("Ввидите минимальный достаточный объем оперативной памяти(8 -- 32):");
                    filters.put("ramOnBoard", scanner.nextInt());
                    System.out.println("Выберите следующий параметр для фильтрации или нажмите «0» для вывода результата.");
                    break;
                case 4:
                    System.out.print("Введите желаемый тип накопителя(HDD, SSD): ");
                    filters.put("driveType", scanner.next());
                    System.out.println("Выберите следующий параметр для фильтрации или нажмите «0» для вывода результата.");
                    break;
                case 5:
                    System.out.print("Введите минимальный объем накопителя(256 -- 1024): ");
                    filters.put("driveSize", scanner.nextInt());
                    System.out.println("Выберите следующий параметр для фильтрации или нажмите «0» для вывода результата.");
                    break;
                case 6:
                    System.out.print("Введите назваение желаемоы операционной системы(Windows 11, MacOS 14 Sonoma, CentOs 7, Не установлена): ");
                    filters.put("installedOS", scanner.next());
                    System.out.println("Выберите следующий параметр для фильтрации или нажмите «0» для вывода результата.");
                    break;
                default:
                    System.out.println("Что-то пошло не так. Попробуйте ещё разок. ");
            }
        }

        Set<Laptop> filteredLaptops = laptops.stream()
                .filter(laptop -> filters.getOrDefault("size", 0) instanceof Integer && laptop.size >= (int) filters.getOrDefault("size", 0))
                .filter(laptop -> filters.getOrDefault("vendor", "").equals("") || laptop.vendor.equalsIgnoreCase((String) filters.getOrDefault("vendor", "")))
                .filter(laptop -> filters.getOrDefault("ramOnBoard", 0) instanceof Integer && laptop.ramOnBoard >= (int) filters.getOrDefault("ramOnBoard", 0))
                .filter(laptop -> filters.getOrDefault("driveType", "").equals("") || laptop.driveType.equalsIgnoreCase((String) filters.getOrDefault("driveType", "")))
                .filter(laptop -> filters.getOrDefault("driveSize", 0) instanceof Integer && laptop.driveSize >= (int) filters.getOrDefault("driveSize", 0))
                .filter(laptop -> filters.getOrDefault("installedOS", "").equals("") || laptop.installedOS.equalsIgnoreCase((String) filters.getOrDefault("installedOS", "")))
                .collect(Collectors.toSet());

        System.out.println("Отфильтрованные ноутбуки:");
        int count = 0;
        for (Laptop laptop : filteredLaptops) {
            System.out.println(++count + ". "+ laptop.vendor + " диагональ:" + laptop.size + " RAM:" + laptop.ramOnBoard + "Gb " + laptop.driveType + " (" + laptop.driveSize + ") " + " ОС: " + laptop.installedOS );
        }
    }
}