package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MenuWindow extends JLayeredPane {
    private final JFrame menuFrame = setJFrame();
    private JFrame frame;
    private ArrayList<int[]> rami = new ArrayList<>();
    private JComboBox<String> u;
    private JComboBox<String> v;
    private final int[][] ponti = new int[4][4];
    private boolean esecuzione = false;
    private boolean aggiungiRamoAperto = false;

    public MenuWindow() {
        // Initialize background image
        setBackgroundImage();
        // Initialize frame
        // Add elements to panel
        this.setLayout(null);

        setupMenuPane();

        // Make frame visible
        menuFrame.add(this);
        menuFrame.setVisible(true);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void removeAllFromPanel() {
        this.removeAll();
        this.revalidate();
        this.repaint();
    }

    private void basePanel() {
        removeAllFromPanel();

        JLabel opaqueRectangle;
        // Menu opaque background
        opaqueRectangle = new JLabel();
        opaqueRectangle.setBackground(new Color(0,0,0, 0.5F));
        opaqueRectangle.setOpaque(true);
        opaqueRectangle.setBounds(0,0,80,500);
        this.add(opaqueRectangle,0);
        // Frame close button
        final JLabel closeButton = new JLabel("✖", SwingConstants.CENTER);
        closeButton.setFont(new Font("Serif", Font.PLAIN, 17));
        closeButton.setForeground(Color.BLACK);
        closeButton.setBounds(770,10,20,20);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                menuFrame.dispose();
            }
            public void mouseEntered(MouseEvent e) {
                closeButton.setForeground(new Color(50, 50, 50));
            }
            public void mouseExited(MouseEvent e) {
                closeButton.setForeground(Color.BLACK);
            }
        });
        this.add(closeButton,0);
    }

    private void setupMenuPane() {
        removeAllFromPanel();

        JLabel opaqueRectangle;
        JLabel explainingButton;
        JLabel executionButton;
        // Menu opaque background
        opaqueRectangle = new JLabel();
        opaqueRectangle.setBackground(new Color(0,0,0, 0.5F));
        opaqueRectangle.setOpaque(true);
        opaqueRectangle.setBounds(0,0,250,500);
        this.add(opaqueRectangle,0);
        // Frame close button
        final JLabel closeButton = new JLabel("✖", SwingConstants.CENTER);
        closeButton.setFont(new Font("Serif", Font.PLAIN, 17));
        closeButton.setForeground(Color.BLACK);
        closeButton.setBounds(770,10,20,20);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                menuFrame.dispose();
            }
            public void mouseEntered(MouseEvent e) {
                closeButton.setForeground(new Color(50, 50, 50));
            }
            public void mouseExited(MouseEvent e) {
                closeButton.setForeground(Color.BLACK);
            }
        });
        this.add(closeButton,0);
        // Situation explaining
        ImageIcon spiegazioneIMG = new ImageIcon("src/assets/buttons/spiegazione.png");
        explainingButton = new JLabel();
        explainingButton.setIcon(spiegazioneIMG);
        explainingButton.setBounds(50,180,150,40);
        explainingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        explainingButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                explainMenu();
            }
        });
        this.add(explainingButton,1);
        // Execution
        ImageIcon problemaIMG = new ImageIcon("src/assets/buttons/problema.png");
        executionButton = new JLabel();
        executionButton.setIcon(problemaIMG);
        executionButton.setBounds(50,240,150,40);
        executionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        executionButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                executeMenu();
            }
        });
        this.add(executionButton,1);
    }

    private void setupExplainPane() {
        esecuzione = false;
        repaint();
        removeAllFromPanel();
        basePanel();

        JLabel explainingButton;
        JLabel executionButton;
        // Situation explaining
        ImageIcon spiegazioneIMG = new ImageIcon("src/assets/buttons/spiegazioneIcoActive.png");
        explainingButton = new JLabel();
        explainingButton.setIcon(spiegazioneIMG);
        explainingButton.setBounds(20,180,40,40);
        explainingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(explainingButton,1);
        // Execution
        ImageIcon problemaIMG = new ImageIcon("src/assets/buttons/problemaIco.png");
        executionButton = new JLabel();
        executionButton.setIcon(problemaIMG);
        executionButton.setBounds(20,240,40,40);
        executionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JTextArea ecusupurationu = new JTextArea ("Il problema dei sette ponti di Königsberg è un dilemma matematico in cui si verifica se sia possibile con una passeggiata seguire un percorso che attraversi ognuno dei sette ponti che collegano le isole di Königsberg alla terraferma una e una volta soltanto.\n" +
                "\n" +
                "Nel 1736 Eulero affrontò tale problema, dimostrando che la passeggiata ipotizzata non era possibile.\n" +
                "\n\n\n\n\n\n\n\n\n\n" +
                "Per la sua risoluzione viene applicata la teoria dei grafi, nella quale la città viene ridotta in un grafo composto da quattro nodi collegati fra loro da sette rami.");
        ecusupurationu.setBounds(140,40,300,420);
        ecusupurationu.setBackground(new Color(0,0,0, 0.5F));
        ecusupurationu.setForeground(Color.WHITE);
        ecusupurationu.setWrapStyleWord(true);
        ecusupurationu.setLineWrap(true);
        ecusupurationu.setEditable(false);
        this.add(ecusupurationu,1);

        ImageIcon pontiImage = new ImageIcon("src/assets/explain/pontiImage.png");
        JLabel explainingImage1 = new JLabel();
        explainingImage1.setIcon(pontiImage);
        explainingImage1.setBounds(480,20,300,240);
        this.add(explainingImage1,1);

        ImageIcon schemaImage = new ImageIcon("src/assets/explain/schemaImage.png");
        JLabel explainingImage2 = new JLabel();
        explainingImage2.setIcon(schemaImage);
        explainingImage2.setBounds(505,260,250,215);
        this.add(explainingImage2,1);

        executionButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                executeMenu();
            }
        });
        this.add(executionButton,1);


    }

    private void setupExecutePane() {
        esecuzione = true;
        repaint();
        removeAllFromPanel();
        basePanel();

        JLabel explainingButton;
        JLabel executionButton;

        // Situation explaining
        ImageIcon spiegazioneIMG = new ImageIcon("src/assets/buttons/spiegazioneIco.png");
        explainingButton = new JLabel();
        explainingButton.setIcon(spiegazioneIMG);
        explainingButton.setBounds(20,180,40,40);
        explainingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        explainingButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                explainMenu();
            }
        });
        this.add(explainingButton,1);
        // Execution
        ImageIcon problemaIMG = new ImageIcon("src/assets/buttons/problemaIcoActive.png");
        executionButton = new JLabel();
        executionButton.setIcon(problemaIMG);
        executionButton.setBounds(20,240,40,40);
        executionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        executionButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { }
        });
        this.add(executionButton,1);

        // Menu execution
        ImageIcon addButtonImg = new ImageIcon("src/assets/buttons/aggiungiPonte.png");
        JLabel addButton = new JLabel("",SwingConstants.CENTER);
        addButton.setIcon(addButtonImg);
        addButton.setBounds(360,420,100,40);
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(!aggiungiRamoAperto){
                    aggiungiRamo();
                }
            }
        });
        this.add(addButton,3);

        JLabel lettA = new JLabel("A",SwingConstants.CENTER);
        lettA.setFont(lettA.getFont ().deriveFont (20.0f));
        lettA.setBounds(315,100,50,50);
        this.add(lettA);
        JLabel lettB = new JLabel("B",SwingConstants.CENTER);
        lettB.setFont(lettB.getFont ().deriveFont (20.0f));
        lettB.setBounds(315,300,50,50);
        this.add(lettB);
        JLabel lettC = new JLabel("C",SwingConstants.CENTER);
        lettC.setFont(lettC.getFont ().deriveFont (20.0f));
        lettC.setBounds(215,200,50,50);
        this.add(lettC);
        JLabel lettD = new JLabel("D",SwingConstants.CENTER);
        lettD.setFont(lettD.getFont ().deriveFont (20.0f));
        lettD.setBounds(415,200,50,50);
        this.add(lettD);

        ImageIcon executeImg = new ImageIcon("src/assets/buttons/vai.png");
        JLabel executeButton = new JLabel("",SwingConstants.CENTER);
        executeButton.setIcon(executeImg);
        executeButton.setBounds(470,420,40,40);
        executeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(executeButton,3);

        JLabel removeAll = new JLabel("Rimuovi tutti",SwingConstants.CENTER);
        removeAll.setVerticalAlignment(SwingConstants.CENTER);
        removeAll.setCursor(new Cursor(Cursor.HAND_CURSOR));
        removeAll.setForeground(Color.white);
        removeAll.setBounds(140,420,100,40);
        removeAll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rami = new ArrayList<>();

                for(int i=0;i<4;i++) {
                    for (int j=0;j<4;j++) {
                        ponti[i][j] = 0;
                    }
                }

                setupExecutePane();
            }
        });
        this.add(removeAll,3);

        // Dx riepilogo
        ImageIcon schemaIMG = new ImageIcon("src/assets/execution/schemaImage.png");
        JLabel schemaLabel = new JLabel("",SwingConstants.CENTER);
        schemaLabel.setIcon(schemaIMG);
        schemaLabel.setBounds(570,40,200,200);
        this.add(schemaLabel,1);

        final JLabel isEulerianPath = new JLabel("Il percorso é euleriano: ",SwingConstants.CENTER);
        isEulerianPath.setVerticalAlignment(SwingConstants.TOP);
        isEulerianPath.setOpaque(true);
        isEulerianPath.setBackground(new Color(0,0,0,0.4F));
        isEulerianPath.setForeground(Color.white);
        isEulerianPath.setBounds(570,320,200,40);
        this.add(isEulerianPath,1);

        final JLabel isEulerianPathResponse = new JLabel("",SwingConstants.CENTER);
        isEulerianPathResponse.setForeground(Color.white);
        isEulerianPathResponse.setBounds(570,340,200,20);
        this.add(isEulerianPathResponse,1);

        final JLabel isCyclePath = new JLabel("Il percorso é un ciclo: ",SwingConstants.CENTER);
        isCyclePath.setVerticalAlignment(SwingConstants.TOP);
        isCyclePath.setOpaque(true);
        isCyclePath.setBackground(new Color(0,0,0,0.4F));
        isCyclePath.setForeground(Color.white);
        isCyclePath.setBounds(570,370,200,40);
        this.add(isCyclePath,1);

        final JLabel isCycleResponse = new JLabel("",SwingConstants.CENTER);
        isCycleResponse.setForeground(Color.white);
        isCycleResponse.setBounds(570,390,200,20);
        this.add(isCycleResponse,1);

        final JTextArea  path = new JTextArea ("");
        path.setBounds(570,430,200,40);
        path.setWrapStyleWord(true);
        path.setLineWrap(true);
        path.setEditable(false);
        this.add(path,1);

        executeButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                EulerianPath g3 = new EulerianPath(4);
                for (int[] ramo:rami) {
                    g3.addEdge(ramo[0],ramo[1]);
                }

                g3.printEulerTour();

                if(g3.isEulerianPath()) {
                    isEulerianPathResponse.setText("true");
                } else {
                    isEulerianPathResponse.setText("false");
                }
                if(g3.isEulerianCycle()) {
                    isCycleResponse.setText("true");
                } else {
                    isCycleResponse.setText("false");
                }

                String percorsoString = "";

                for (int nodo: g3.percorso) {
                    percorsoString = percorsoString.concat(String.valueOf(nodo));
                    percorsoString = percorsoString.concat(" - ");
                }

                percorsoString = percorsoString.substring(0, percorsoString.length()-3);

                path.setText(percorsoString);
            }
        });

        stampaQuantita();
    }

    private void stampaQuantita() {
        repaint();

        if(ponti[0][1]>3) {
            System.out.println(ponti[0][1]);
            JLabel quantAB = new JLabel(String.valueOf(ponti[0][1]),SwingConstants.CENTER);
            quantAB.setText(String.valueOf(ponti[0][1]));
            quantAB.setBounds(325,170,30,30);
            quantAB.setFont(quantAB.getFont ().deriveFont (18.0f));
            quantAB.setBackground(Color.darkGray);
            quantAB.setForeground(Color.white);
            quantAB.setOpaque(true);
            this.add(quantAB);
        }
        if(ponti[2][3]>3) {
            JLabel quantCD = new JLabel(String.valueOf(ponti[2][3]),SwingConstants.CENTER);
            quantCD.setBounds(280,210,30,30);
            quantCD.setFont(quantCD.getFont ().deriveFont (18.0f));
            quantCD.setBackground(Color.darkGray);
            quantCD.setForeground(Color.white);
            quantCD.setOpaque(true);
            this.add(quantCD);
        }
        if(ponti[2][0]>3) {
            JLabel quantCA = new JLabel(String.valueOf(ponti[2][0]),SwingConstants.CENTER);
            quantCA.setBounds(275,160,30,30);
            quantCA.setFont(quantCA.getFont ().deriveFont (18.0f));
            quantCA.setBackground(Color.darkGray);
            quantCA.setForeground(Color.white);
            quantCA.setOpaque(true);
            this.add(quantCA);
        }
        if(ponti[0][3]>3) {
            JLabel quantAD = new JLabel(String.valueOf(ponti[0][3]), SwingConstants.CENTER);
            quantAD.setBounds(375, 160, 30, 30);
            quantAD.setFont(quantAD.getFont().deriveFont(18.0f));
            quantAD.setBackground(Color.darkGray);
            quantAD.setForeground(Color.white);
            quantAD.setOpaque(true);
            this.add(quantAD);
        }
        if(ponti[2][1]>3) {
            JLabel quantCB = new JLabel(String.valueOf(ponti[2][1]),SwingConstants.CENTER);
            quantCB.setBounds(275,260,30,30);
            quantCB.setFont(quantCB.getFont ().deriveFont (18.0f));
            quantCB.setBackground(Color.darkGray);
            quantCB.setForeground(Color.white);
            quantCB.setOpaque(true);
            this.add(quantCB);
        }
        if(ponti[3][1]>3) {
            JLabel quantDB = new JLabel(String.valueOf(ponti[3][1]),SwingConstants.CENTER);
            quantDB.setBounds(375,260,30,30);
            quantDB.setFont(quantDB.getFont ().deriveFont (18.0f));
            quantDB.setBackground(Color.darkGray);
            quantDB.setForeground(Color.white);
            quantDB.setOpaque(true);
            this.add(quantDB);
        }
    }

    private void aggiungiRamo() {
        aggiungiRamoAperto=true;

        frame = new JFrame("Nuovo ramo");
        frame.setLocationRelativeTo(null);
        frame.setSize(200,140);
        frame.setUndecorated(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label1 = new JLabel("Da");
        label1.setBounds(10,10,100,30);
        panel.add(label1);
        String[] uOptions = {"A", "B", "C", "D"};
        u = new JComboBox<>(uOptions);
        u.setBounds(50,10,100,30);
        panel.add(u);

        JLabel label2 = new JLabel("a");
        label2.setBounds(10,50,100,30);
        panel.add(label2);
        String[] vOptions = {"A", "B", "C", "D"};
        v = new JComboBox<>(vOptions);
        v.setBounds(50,50,100,30);
        panel.add(v);

        JButton annullaButton = new JButton("Annulla");
        annullaButton.setBounds(10,100,80,30);
        annullaButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                aggiungiRamoAperto=false;
                frame.dispose();
            }
        });
        panel.add(annullaButton);

        JButton confermaButton = new JButton("Conferma");
        confermaButton.setBounds(100,100,90,30);
        confermaButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int[] nuovoRamo = {0,0};

                String uValue = Objects.requireNonNull(u.getSelectedItem()).toString();
                String vValue = Objects.requireNonNull(v.getSelectedItem()).toString();

                switch (uValue) {
                    case "B":
                        nuovoRamo[0] = 1;
                        break;
                    case "C":
                        nuovoRamo[0] = 2;
                        break;
                    case "D":
                        nuovoRamo[0] = 3;
                        break;
                }

                switch (vValue) {
                    case "B":
                        nuovoRamo[1] = 1;
                        break;
                    case "C":
                        nuovoRamo[1] = 2;
                        break;
                    case "D":
                        nuovoRamo[1] = 3;
                        break;
                }

                rami.add(nuovoRamo);
                ponti[nuovoRamo[0]][nuovoRamo[1]]++;
                ponti[nuovoRamo[1]][nuovoRamo[0]]++;

                String ponti = "";

                for (int[] ramo:rami) {
                    ponti = ponti.concat("Da ");
                    switch (ramo[0]) {
                        case 0:
                            ponti = ponti.concat("A");
                            break;
                        case 1:
                            ponti = ponti.concat("B");
                            break;
                        case 2:
                            ponti = ponti.concat("C");
                            break;
                        case 3:
                            ponti = ponti.concat("D");
                            break;
                    }
                    ponti = ponti.concat(" a ");
                    switch (ramo[1]) {
                        case 0:
                            ponti = ponti.concat("A");
                            break;
                        case 1:
                            ponti = ponti.concat("B");
                            break;
                        case 2:
                            ponti = ponti.concat("C");
                            break;
                        case 3:
                            ponti = ponti.concat("D");
                            break;
                    }
                    ponti = ponti.concat("\n");
                }
                aggiungiRamoAperto=false;


                setupExecutePane();
                repaint();

                frame.dispose();
            }
        });
        panel.add(confermaButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void explainMenu() {
        setupExplainPane();
    }

    private void executeMenu() {
        for(int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {
                ponti[i][j] = 0;
            }
        }
        setupExecutePane();
    }

    private JFrame setJFrame() {
        JFrame menuFrame = new JFrame();

        menuFrame.setSize(800,500);
        menuFrame.setResizable(false);
        menuFrame.setUndecorated(true);
        menuFrame.setLocationRelativeTo(null);

        return menuFrame;
    }

    private void setBackgroundImage() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src\\assets\\konigsberg.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.paintComponent(g);
        g.drawImage(image,0,0,800,500,this);

        if(esecuzione) {
            Graphics2D g2 = (Graphics2D) g;

            g2.setColor(new Color(0,0,0,0.5F));
            g2.fillRect(570,40,200,420);
            g2.fillRect(140,40,400,360);

            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(5));

            g2.fillOval(315,100,50,50);
            g2.fillOval(315,300,50,50);
            g2.fillOval(215,200,50,50);
            g2.fillOval(415,200,50,50);

            switch (ponti[0][1]) {
                case 0:
                    break;
                case 1:
                    g2.drawLine(340,125,340,325);
                    break;
                case 2:
                    g2.drawLine(330,125,330,325);
                    g2.drawLine(350,125,350,325);
                    break;
                case 3:
                    g2.drawLine(330,125,330,325);
                    g2.drawLine(340,125,340,325);
                    g2.drawLine(350,125,350,325);
                    break;
                default:
                    g2.setStroke(new BasicStroke(8));
                    g2.drawLine(340,125,340,325);
                    g2.setStroke(new BasicStroke(5));
            }
            switch (ponti[0][3]) {
                case 0:
                    break;
                case 1:
                    g2.drawLine(340,125,440,225);
                    break;
                case 2:
                    g2.drawLine(335,130,435,230);
                    g2.drawLine(345,120,445,220);
                    break;
                case 3:
                    g2.drawLine(335,130,435,230);
                    g2.drawLine(340,125,440,225);
                    g2.drawLine(345,120,445,220);
                    break;
                default:
                    g2.setStroke(new BasicStroke(8));
                    g2.drawLine(340,125,440,225);
                    g2.setStroke(new BasicStroke(5));
            }
            switch (ponti[0][2]) {
                case 0:
                    break;
                case 1:
                    g2.drawLine(340,125,240,225);
                    break;
                case 2:
                    g2.drawLine(335,120,235,220);
                    g2.drawLine(345,130,245,230);
                    break;
                case 3:
                    g2.drawLine(335,120,235,220);
                    g2.drawLine(340,125,240,225);
                    g2.drawLine(345,130,245,230);
                    break;
                default:
                    g2.setStroke(new BasicStroke(8));
                    g2.drawLine(340,125,240,225);
                    g2.setStroke(new BasicStroke(5));
            }
            switch (ponti[2][3]) {
                case 0:
                    break;
                case 1:
                    g2.drawLine(240,225,440,225);
                    break;
                case 2:
                    g2.drawLine(240,215,440,215);
                    g2.drawLine(240,235,440,235);
                    break;
                case 3:
                    g2.drawLine(240,215,440,215);
                    g2.drawLine(240,225,440,225);
                    g2.drawLine(240,235,440,235);
                    break;
                default:
                    g2.setStroke(new BasicStroke(8));
                    g2.drawLine(240,225,440,225);
                    g2.setStroke(new BasicStroke(5));
            }
            switch (ponti[2][1]) {
                case 0:
                    break;
                case 1:
                    g2.drawLine(240,225,340,325);
                    break;
                case 2:
                    g2.drawLine(235,230,335,330);
                    g2.drawLine(245,220,345,320);
                    break;
                case 3:
                    g2.drawLine(235,230,335,330);
                    g2.drawLine(240,225,340,325);
                    g2.drawLine(245,220,345,320);
                    break;
                default:
                    g2.setStroke(new BasicStroke(8));
                    g2.drawLine(240,225,340,325);
                    g2.setStroke(new BasicStroke(5));
            }
            switch (ponti[3][1]) {
                case 0:
                    break;
                case 1:
                    g2.drawLine(440,225,340,325);
                    break;
                case 2:
                    g2.drawLine(435,220,335,320);
                    g2.drawLine(445,230,345,330);
                    break;
                case 3:
                    g2.drawLine(435,220,335,320);
                    g2.drawLine(440,225,340,325);
                    g2.drawLine(445,230,345,330);
                    break;
                default:
                    g2.setStroke(new BasicStroke(8));
                    g2.drawLine(440,225,340,325);
                    g2.setStroke(new BasicStroke(5));
            }
        }
    }
}