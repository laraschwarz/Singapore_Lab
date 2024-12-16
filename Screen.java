import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;

public class Screen extends JPanel implements ActionListener, MouseListener {
    Graph<Location> graph = new Graph<Location>();

    private JTextField start;
    private JTextField end;

    private Image map;
    private Image flag;

    private JButton findPathButton;

    private JTextArea textArea;
    private JScrollPane scrollPane;

    private String text;
    private int fieldSize;
    private int xCoor;
    private String shortest;
    private Car car;
    private DLList<Location> shortestPath;
    private boolean move;

    public Screen() {
        this.setLayout(null);
        addMouseListener(this);

        text = "";
        fieldSize = 120;
        xCoor = 1300;
        shortest = "";
        shortestPath = new DLList<Location>();
        car = new Car(50, 50);
        move = false;

        Location qut = new Location("Queenstown", "qut", 556, 564);// 1
        Location chn = new Location("Changi", "chn", 1156, 400);
        Location ktg = new Location("Katong", "ktg", 915, 515);
        Location bdk = new Location("Bedok", "bdk", 922, 482);
        Location tus = new Location("Tuas", "tus", 110, 550);// 5
        Location pnr = new Location("Pioneer", "pnr", 258, 474);
        Location gyg = new Location("Geylang", "gyg", 904, 460);
        Location bnl = new Location("Boon Lay", "bnl", 343, 494);
        Location ysn = new Location("Yishun", "ysn", 714, 156);
        Location wds = new Location("Woodlands", "wds", 582, 129);// 10
        Location clm = new Location("Celemti", "clm", 507, 482);
        Location nvm = new Location("Novena", "nvn", 737, 473);
        Location tpy = new Location("Toa Payoh", "tpy", 779, 433);
        Location bth = new Location("Bukit Timah", "bth", 618, 450);
        Location slr = new Location("Seletar", "slr", 805, 226);// 15
        Location pgl = new Location("Punggol", "pgl", 927, 249);
        Location skg = new Location("Sengkang", "skg", 883, 283);
        Location krj = new Location("Kranji", "krj", 451, 183);
        Location sbw = new Location("Sembawang", "sbw", 665, 93);
        Location mdi = new Location("Mandai", "mdi", 683, 179);// 20

        graph.add(qut);
        graph.add(chn);
        graph.add(ktg);
        graph.add(bdk);
        graph.add(tus);// 5
        graph.add(pnr);
        graph.add(gyg);
        graph.add(bnl);
        graph.add(ysn);
        graph.add(wds);// 10
        graph.add(clm);
        graph.add(nvm);
        graph.add(tpy);
        graph.add(bth);
        graph.add(slr);// 15
        graph.add(pgl);
        graph.add(skg);
        graph.add(krj);
        graph.add(sbw);
        graph.add(mdi);

        graph.addEdge(qut, ktg, 19);
        graph.addEdge(clm, ktg, 22);
        graph.addEdge(clm, bnl, 12);
        graph.addEdge(clm, krj, 14);
        graph.addEdge(mdi, slr, 10);
        graph.addEdge(mdi, sbw, 3);

        graph.addEdge(skg, pgl, 3);

        graph.addEdge(bdk, gyg, 4);
        graph.addEdge(chn, ktg, 13);
        graph.addEdge(ysn, chn, 27);
        graph.addEdge(wds, ktg, 27);
        graph.addEdge(nvm, tpy, 3);
        graph.addEdge(tpy, chn, 17);
        graph.addEdge(bth, ktg, 16);
        graph.addEdge(gyg, chn, 13);
        graph.addEdge(slr, tpy, 11);
        graph.addEdge(tus, bnl, 12);
        graph.addEdge(bnl, pnr, 6);
        graph.addEdge(skg, gyg, 11);

        // Buttons
        findPathButton = new JButton("Find Path");
        findPathButton.setBounds(xCoor, 150, fieldSize, 30);
        findPathButton.addActionListener(this);
        this.add(findPathButton);

        // TextField
        start = new JTextField(20);
        start.setBounds(xCoor, 50, fieldSize, 30);
        this.add(start);

        end = new JTextField(20);
        end.setBounds(xCoor, 100, fieldSize, 30);
        this.add(end);

        // TextArea
        textArea = new JTextArea(); // sets the location and size
        textArea.setBounds(1100, 550, 350, 250);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 13));

        text = shortest;

        textArea.setText(text);
        this.add(textArea);

        // JScrollPane
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(1100, 550, 350, 250);
        this.add(scrollPane);
        this.setFocusable(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1500, 850);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        Font font = new Font("Skia", Font.PLAIN, 10);
        Font font2 = new Font("Skia", Font.PLAIN, 14);

        g.setFont(font);

        // draw background
        g.setColor(new Color(129, 179, 131));
        g.fillRect(0, 0, 1500, 850);

        g.setColor(Color.black);

        textArea.setText(text);

        try {
            map = ImageIO.read(new File("singaporeMap.png"));
            flag = ImageIO.read(new File("flag.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        g.drawImage(map, 10, 10, 1480, 830, null);

        g.drawString("From: ", xCoor, 45);
        // g.drawString("URL: ", x - 150, 275);

        g.drawString("To: ", xCoor, 95);

        g.setFont(font2);

        DLList<Location> keyList = graph.keySet().toDLList();
        for (int i = 0; i < keyList.size(); i++) {
            Location loc = keyList.get(i);
            //g.drawString(loc.toString(), loc.getX(), loc.getY());

            // draw connecting lines
            DLList<Location> connections = graph.getEdges(loc).keySet().toDLList();
            for (int n = 0; n < connections.size(); n++) {
                if(shortestPath.contains(connections.get(n)) && shortestPath.contains(loc)){
                    g.setColor(Color.RED);
                }
                else{
                    g.setColor(Color.BLACK);
                }
                g.drawLine(loc.getX(), loc.getY(), connections.get(n).getX(), connections.get(n).getY());
                g.setColor(Color.BLACK);
            }
        }
        MyHashSet<Location> keySet = graph.keySet();
        for(int i = 0; i < shortestPath.size()-1; i++){
            int x1 = keySet.get(shortestPath.get(i).hashCode()).getX();
            int y1 = keySet.get(shortestPath.get(i).hashCode()).getY();
            int x2 = keySet.get(shortestPath.get(i+1).hashCode()).getX();
            int y2 = keySet.get(shortestPath.get(i+1).hashCode()).getY();
            g2d.setStroke(new BasicStroke(3));
            g.setColor(Color.RED);
            g.drawLine(x1, y1, x2, y2);        
        }
        g.setColor(Color.BLACK);
        for (int i = 0; i < keyList.size(); i++) {
            Location loc = keyList.get(i);
            g.drawString(loc.toString(), loc.getX(), loc.getY());
        }
        car.drawMe(g);

        if(shortestPath.size() > 0){
            int x = keySet.get(shortestPath.get(shortestPath.size()-1).hashCode()).getX();
            int y = keySet.get(shortestPath.get(shortestPath.size()-1).hashCode()).getY();
            g.drawImage(flag, x-15, y-35, 35, 35, null);

        }
    }

    public String directionsToString(DLList<Location> shortest) {
        String text = "";
        int total = 0;
        MyHashSet<Location> keySet = graph.keySet();

        for (int i = 0; i < shortest.size() - 1; i++) {
            Location loc1 = shortest.get(i);
            Location loc2 = shortest.get(i + 1);
            int distance = graph.getWeight(loc1, loc2);
            total += distance;
            text += "Take " + keySet.get(loc1.hashCode()).getName() + " to " + keySet.get(loc2.hashCode()).getName()
                    + " - " + distance + " km\n";
        }

        return text + "\nTotal Distance: " + total + " km";
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == findPathButton) {
            move = false;
            String from = start.getText();
            String to = end.getText();
            Location l1 = new Location("", from, 0, 0);
            Location l2 = new Location("", to, 0, 0);
            shortestPath = graph.shortestPath(l1, l2);
            // shortest = shortestPath.toString();
            shortest = directionsToString(shortestPath);
            text = shortest;
            move = true;
            MyHashSet<Location> keySet = graph.keySet();
            int x1 = keySet.get(shortestPath.get(0).hashCode()).getX();
            int y1 = keySet.get(shortestPath.get(0).hashCode()).getY();
            car.set(x1, y1);

            // int c1 = Integer.parseInt(connect1Field.getText());
        }
        repaint();
    }

    public void mousePressed(MouseEvent e) {

        // Print location of x and y
        System.out.println("X: " + e.getX() + ", Y: " + e.getY());

        repaint();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void animate() {
        double xIncr = 0;
        double yIncr = 0;
        MyHashSet<Location> keySet = graph.keySet();
        while (true) {
            if (move) {

                for (int i = 1; i < shortestPath.size(); i++) {
                    int startX = keySet.get(shortestPath.get(i - 1).hashCode()).getX();
                    int startY = keySet.get(shortestPath.get(i - 1).hashCode()).getY();
                    int x1 = keySet.get(shortestPath.get(i).hashCode()).getX();
                    int y1 = keySet.get(shortestPath.get(i).hashCode()).getY();

                    int diffX = x1 - startX;
                    int diffY = y1 - startY;

                    double slope = ((double) diffY) / (diffX);

                    xIncr = 1 / (Math.sqrt(Math.abs(slope) + 1));
                    if (diffX < 0) {
                        xIncr *= -1;
                    }
                    yIncr = xIncr * slope;

                    while (move) {
                        int x = keySet.get(shortestPath.get(i).hashCode()).getX();
                        int y = keySet.get(shortestPath.get(i).hashCode()).getY();

                        car.moveTowards(x, y, xIncr, yIncr);

                        if (Math.abs(car.getX() - x) < 10 && Math.abs(car.getY() - y) < 10) {
                            break;
                        }
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        repaint();
                    }

                }
                move = false;

            }

            // wait for .01 second
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            // repaint the graphics drawn
            repaint();
        }
    }

}
