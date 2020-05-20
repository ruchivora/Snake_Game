
package snake_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Ruchi Vora
 */
public class GamePlay extends JPanel implements KeyListener,ActionListener{

    private int snakexlength[] = new int[750] ;
    private int snakeylength[] = new int[750] ;
    
    private boolean left  = false ;
    private boolean right = false ;
    private boolean up    = false ;
    private boolean down  = false ;
    
    private ImageIcon rightmouth ;
    private ImageIcon leftmouth ;
    private ImageIcon upmouth ;
    private ImageIcon downmouth ;
    private int lengthofsnake = 3; 
    
    private Timer timer ; /* Timer maintains the speed of the snake */
    private int delay = 100 ;  /*Defines speed of timer */
    private ImageIcon snakeImage ;
    
    /*
        For enemy amongst these positions , one of the x and y position 
        is picked randomly .
        All these positions are multiples of 25 because our snake is 
        adjusted accordingly .
    */
    private int enemyxpos[] = { 25,50,75,100,125,150,175,200,225,250,275,300,
                                325,350,375,400,425,450,475,500,525,550,575,
                                600,625,650,675,700,725,750,775,800,825,850
                               } ;
    private int enemyypos[] = { 75,100,125,150,175,200,225,250,275,300,
                                325,350,375,400,425,450,475,500,525,550,575,
                                600,625
                               } ;
    private ImageIcon enemyimage ; 
    private Random random = new Random() ;
    private int xpos = random.nextInt(34) ;
    private int ypos = random.nextInt(23) ;
    
    private int score = 0 ;
    
    
    private ImageIcon titleImage ;
    
    private int moves = 0 ;
    
    public GamePlay() 
    {
        addKeyListener(this) ;
        setFocusable(true) ;
        timer = new Timer(delay,this) ;
        timer.start();
    }
    /*
        Paint is built in method of java , that draws
        objects . So by taking reference of g all the
        objects are drawn .
    */
    public void paint(Graphics g)
    {
        if( moves == 0 )
        {
            snakexlength[2] = 50  ;
            snakexlength[1] = 75  ;
            snakexlength[0] = 100 ;
            
            snakeylength[2] = 100;
            snakeylength[1] = 100 ;
            snakeylength[0] = 100 ;
        }
        /* Draw title image border */
        g.setColor(Color.WHITE);
        g.drawRect(24,10,851,55);
        
        /*Draw the title image*/
        titleImage = new ImageIcon(ClassLoader.getSystemResource("snake_game/game_icon/snaketitle.jpg"));
        titleImage.paintIcon(this, g, 25, 11);
        
        /*Draw border for Game*/
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);
        
        /*set background color for game*/
        g.setColor(Color.BLACK);
        g.fillRect(25,75,850,575);
        
        /*Writing score*/
        g.setColor(Color.WHITE) ;
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Score : "+score, 780, 30);
        
        /* Writing length of snake */
        g.setColor(Color.WHITE) ;
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Length : "+lengthofsnake,780, 50);
        
        /*draw the snake*/
        rightmouth = new ImageIcon(ClassLoader.getSystemResource("snake_game/game_icon/rightmouth.png"));
        rightmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
        
        for( int a = 0 ; a < lengthofsnake ; a++ )
        {
            /*
                If a==0 that means face of the snake .
                If a>0 that means body of the snake .
            */
            if( a == 0 && right )
            {
                 rightmouth = new ImageIcon(ClassLoader.getSystemResource("snake_game/game_icon/rightmouth.png"));
                 rightmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);
            }
            if( a == 0 && left )
            {
                 leftmouth = new ImageIcon(ClassLoader.getSystemResource("snake_game/game_icon/leftmouth.png"));
                 leftmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);
            }
            if( a == 0 && down)
            {
                 downmouth = new ImageIcon(ClassLoader.getSystemResource("snake_game/game_icon/downmouth.png"));
                 downmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);
            }
            if( a == 0 && up)
            {
                 upmouth = new ImageIcon(ClassLoader.getSystemResource("snake_game/game_icon/upmouth.png"));
                 upmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);
            }
            if( a != 0 )
            {
                 snakeImage = new ImageIcon(ClassLoader.getSystemResource("snake_game/game_icon/snakeimage.png"));
                 snakeImage.paintIcon(this,g,snakexlength[a],snakeylength[a]);
            }
        }
        /*
            If the enemy is colliding with snake .
        */
        enemyimage = new ImageIcon(ClassLoader.getSystemResource("snake_game/game_icon/enemy.png")); 
        /*
            snakexpos[0] == head of the snake .
        */
        if(enemyxpos[xpos] == snakexlength[0] && enemyypos[ypos] == snakeylength[0] )
        {
            score +=5 ;
            lengthofsnake++ ;
            xpos = random.nextInt(34)  ;
            ypos = random.nextInt(23) ;
        }
        enemyimage.paintIcon(this, g, enemyxpos[xpos],enemyypos[ypos]);
        
        /*
            Detecting collision of snake with the body .
        */
        for(int i = 1 ; i < lengthofsnake ; i++ )
        {
            if( snakexlength[i] == snakexlength[0] && snakeylength[i] == snakeylength[0] )
            {
                right = false ;
                left  = false ;
                up    = false ;
                down  = false ;
                
                g.setColor(Color.WHITE) ;
                g.setFont(new Font( "arial" ,Font.BOLD , 50 )) ;
                g.drawString("Game Over",300,300) ;
                
                g.setFont(new Font( "arial" ,Font.BOLD , 20 )) ;
                g.drawString("Press Space to Restart ",320,340) ;
         
            }    
        }
        
        
        
        g.dispose();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            moves = 0 ;
            score = 0 ;
            lengthofsnake = 3 ;
            repaint() ;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT )
        {
            moves++ ;
            right = true  ;
            if( left == false) 
            {
                right  = true  ;   
            }
            else
            {
                left  = true ;
                right = false ;
            }
            up    = false ;
            down  = false ; 
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT )
        {
            moves++ ;
            left = true  ;
            if( right == true )
            {
                right = true ;
                left  = false ;
            }
            up    = false ;
            down  = false ; 
        }
        if(e.getKeyCode() == KeyEvent.VK_UP )
        {
            moves++ ;
            right = false ;
            left  = false ;
            up    = true  ;
           
            if( down == true )
            {
                up   = false ;
                down = true  ;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN )
        {
            moves++ ;
            right = false ;
            left  = false ;
            down  = true  ; 
            
            if( up == true )
            {
                down = false ;
                up   = true ;
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
      
      timer.start() ;
      
      if( right )
      {
          for( int r = lengthofsnake - 1 ; r >= 0 ; r-- )
          {
              snakeylength[r + 1] = snakeylength[r] ; 
          }
          
          for( int r = lengthofsnake ; r >= 0 ; r-- )
          {
              if( r == 0 )
              {
                snakexlength[r] = snakexlength[r] + 25 ;
              }
              else{
                  snakexlength[r] = snakexlength[r - 1] ;
              }
              if( snakexlength[r] > 850)
              {
                snakexlength[r] = 25 ;
              }
          }
           repaint();
       }
        
       if( left )
       {
          for( int r = lengthofsnake - 1 ; r >= 0 ; r-- )
          {
              snakeylength[r + 1] = snakeylength[r] ; 
          }
          
          for( int r = lengthofsnake ; r >= 0 ; r-- )
          {
              if( r == 0 )
              {
                snakexlength[r] = snakexlength[r] - 25 ;
              }
              else{
                  snakexlength[r] = snakexlength[r - 1] ;
              }
              if( snakexlength[r] < 25)
              {
                snakexlength[r] = 850 ;
              }
          }
           repaint();
        }
       if( up )
       {
          for( int r = lengthofsnake - 1 ; r >= 0 ; r-- )
          {
              snakexlength[r + 1] = snakexlength[r] ; 
          }
          
          for( int r = lengthofsnake ; r >= 0 ; r-- )
          {
              if( r == 0 )
              {
                snakeylength[r] = snakeylength[r] - 25 ;
              }
              else{
                  snakeylength[r] = snakeylength[r - 1] ;
              }
              if( snakeylength[r] < 75)
              {
                snakeylength[r] = 625 ;
              }
          }
           repaint();
        }
        if( down )
        {
          for( int r = lengthofsnake - 1 ; r >= 0 ; r-- )
          {
              snakexlength[r + 1] = snakexlength[r] ; 
          }
          
          for( int r = lengthofsnake ; r >= 0 ; r-- )
          {
              if( r == 0 )
              {
                snakeylength[r] = snakeylength[r] + 25 ;
              }
              else{
                  snakeylength[r] = snakeylength[r - 1] ;
              }
              if( snakeylength[r] > 625)
              {
                snakeylength[r] = 75 ;
              }
          }
           repaint();
        }
      
     
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}
