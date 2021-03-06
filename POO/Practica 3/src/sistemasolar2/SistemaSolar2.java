package sistemasolar2;

/*@author Home */
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class SistemaSolar2 implements ActionListener {

    private final JComboBox jcb;

    public SistemaSolar2() {
        
        BranchGroup group = new BranchGroup();
        
        Appearance appsol = new Appearance();
        Appearance appmercurio = new Appearance();
        Appearance appvenus = new Appearance();
        Appearance appearth = new Appearance();
        Appearance appmarte = new Appearance();
        Appearance appjupiter = new Appearance();
        Appearance appsaturno = new Appearance();
        Appearance appurano = new Appearance();
        Appearance appneptuno = new Appearance();
        Appearance apppluton = new Appearance();
        
        TextureLoader tex = new TextureLoader("C:\\textura\\TIERRA.JPG", null);
        appearth.setTexture(tex.getTexture());
        tex = new TextureLoader("C:\\textura\\SOL.JPG", null);
        appsol.setTexture(tex.getTexture());
        tex = new TextureLoader("C:\\textura\\MERCURIO.JPG", null);
        appmercurio.setTexture(tex.getTexture());
        tex = new TextureLoader("C:\\textura\\VENUS.JPG", null);
        appvenus.setTexture(tex.getTexture());
        tex = new TextureLoader("C:\\textura\\MARTE.JPG",null);
        appmarte.setTexture(tex.getTexture());
        tex = new TextureLoader("C:\\textura\\JUPITER.JPG",null);
        appjupiter.setTexture(tex.getTexture());
        tex = new TextureLoader("C:\\textura\\SATURNO.JPG", null);
        appsaturno.setTexture(tex.getTexture());
        tex = new TextureLoader("C:\\textura\\URANO.JPG", null);
        appurano.setTexture(tex.getTexture());
        tex = new TextureLoader("C:\\textura\\NEPTUNO.JPG", null);
        appneptuno.setTexture(tex.getTexture());
        tex = new TextureLoader("C:\\textura\\PLUTON.JPG", null);
        apppluton.setTexture(tex.getTexture());
        
        Sphere earth = new Sphere(0.063f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appearth);
        Sphere sol = new Sphere(0.35f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appsol);
        Sphere mercurio = new Sphere(0.045f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appmercurio);
        Sphere venus = new Sphere(0.055f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appvenus);
        Sphere marte = new Sphere(0.055f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appmarte);
        Sphere jupiter = new Sphere(0.093f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appjupiter);
        Sphere saturno = new Sphere(0.099f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appsaturno);
        Sphere urano = new Sphere(0.105f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appurano);
        Sphere neptuno = new Sphere(0.110f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appneptuno);
        Sphere pluton = new Sphere(0.050f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, apppluton);
        
        TransformGroup solRotXformGroup = rotate(sol, new Alpha(-1, 1250));
        TransformGroup mercurioRotXformGroup = rotate(mercurio, new Alpha(-1,1000));
        TransformGroup venusRotXformGroup = rotate(venus, new Alpha(-1,2000));
        TransformGroup earthRotXformGroup = rotate(earth, new Alpha(-1, 3000));
        TransformGroup marteRotXformGroup = rotate(marte, new Alpha(-1,4000));
        TransformGroup jupiterRotXformGroup = rotate(jupiter, new Alpha(-1,5000));
        TransformGroup saturnoRotXformGroup = rotate(saturno, new Alpha(-1,6000));
        TransformGroup uranoRotXformGroup = rotate(urano, new Alpha(-1,7000));
        TransformGroup neptunoRotXformGroup = rotate(neptuno, new Alpha(-1,8000));
        TransformGroup plutonRotXformGroup = rotate(pluton, new Alpha(-1,9000));
        
        
        TransformGroup mercurioTransXformGroup = translate(mercurioRotXformGroup, new Vector3f(0.0f,0.0f, 0.384f));
        TransformGroup mercurioRotGroupXformGroup = rotate(mercurioTransXformGroup, new Alpha(-1,4800));
        TransformGroup venusTransXformGroup = translate(venusRotXformGroup, new Vector3f(0.0f,0.0f, 0.44f));
        TransformGroup venusRotGroupXformGroup = rotate(venusTransXformGroup, new Alpha(-1,5400));
        TransformGroup earthTransXformGroup = translate(earthRotXformGroup, new Vector3f(0.0f, 0.0f, 0.50f));
        TransformGroup earthRotGroupXformGroup = rotate(earthTransXformGroup, new Alpha(-1, 6000));
        TransformGroup marteTransXfromGroup = translate(marteRotXformGroup, new Vector3f(0.0f, 0.0f, 0.64f)); //*Distancia de traslacion
        TransformGroup marteRotGroupXformGroup =  rotate(marteTransXfromGroup,new Alpha(-1,6600));//*Rapidez de rotacion
        TransformGroup jupiterTransXformGroup = translate(jupiterRotXformGroup, new Vector3f(0.0f,0.0f, 0.70f));
        TransformGroup jupiterRotGroupXformGroup = rotate(jupiterTransXformGroup, new Alpha(-1,7200));
        TransformGroup saturnoTransXformGroup = translate(saturnoRotXformGroup, new Vector3f(0.0f,0.0f, 0.76f));
        TransformGroup saturnoRotGroupXformGroup = rotate(saturnoTransXformGroup, new Alpha(-1,7800));
        TransformGroup uranoTransXformGroup = translate(uranoRotXformGroup, new Vector3f(0.0f,0.0f, 0.82f));
        TransformGroup uranoRotGroupXformGroup = rotate(uranoTransXformGroup, new Alpha(-1,8400));
        TransformGroup neptunoTransXformGroup = translate(neptunoRotXformGroup, new Vector3f(0.0f,0.0f, 0.88f));
        TransformGroup neptunoRotGroupXformGroup = rotate(neptunoTransXformGroup, new Alpha(-1,9000));
        TransformGroup plutonTransXformGroup = translate(plutonRotXformGroup, new Vector3f(0.0f,0.0f, 0.94f));
        TransformGroup plutonRotGroupXformGroup = rotate(plutonTransXformGroup, new Alpha(-1,9600));
        
        
        group.addChild(mercurioRotGroupXformGroup);
        group.addChild(venusRotGroupXformGroup);
        group.addChild(earthRotGroupXformGroup);
        group.addChild(marteRotGroupXformGroup);
        group.addChild(jupiterRotGroupXformGroup);
        group.addChild(solRotXformGroup);
        group.addChild(saturnoRotGroupXformGroup);
        group.addChild(uranoRotGroupXformGroup);
        group.addChild(neptunoRotGroupXformGroup);
        group.addChild(plutonRotGroupXformGroup);
        
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas = new Canvas3D(config);
        canvas.setSize(400, 400);
        
        SimpleUniverse universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();
        universe.addBranchGraph(group);
        
        Vector nomPlanet = new Vector();
        nomPlanet.addElement("Sol");
        nomPlanet.addElement("Mercurio");
        nomPlanet.addElement("Venus");
        nomPlanet.addElement("Tierra");
        nomPlanet.addElement("Marte");
        nomPlanet.addElement("Jupiter");
        nomPlanet.addElement("Saturno");
        nomPlanet.addElement("Urano");
        nomPlanet.addElement("Neptuno");
        nomPlanet.addElement("Pluton");
        jcb = new JComboBox(nomPlanet);
        jcb.addActionListener(this);
        JPanel jp = new JPanel();
        jp.add(jcb);
        JFrame f = new JFrame("Planetario");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.add("Center", canvas);
        f.add("South", jp);
        f.pack();
        f.setVisible(true);
    }

    TransformGroup rotate(Node node, Alpha alpha) {
        TransformGroup xformGroup = new TransformGroup();
        xformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        RotationInterpolator interpolator = new RotationInterpolator(alpha, xformGroup);
        interpolator.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1.0));
        xformGroup.addChild(interpolator);
        xformGroup.addChild(node);
        return xformGroup;
    }

    TransformGroup translate(Node node, Vector3f vector) {
        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(vector);
        TransformGroup transformGroup = new TransformGroup();
        transformGroup.setTransform(transform3D);
        transformGroup.addChild(node);
        return transformGroup;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println((String) jcb.getSelectedItem());
    }
   public static void main(String a[]) {
        new SistemaSolar2();
   }
}