/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogl;

import java.util.ArrayList;

import javax.media.opengl.GL;

import jogl.intefaces.ColorModel;
import jogl.intefaces.DrawModel;
import jogl.intefaces.FormaTriangulos;
import jogl.util.Triangulo;
import jogl.util.Vetor3f;

/**
 *
 * Esta classe armazena varios points para desenho de
 * GL_TRIANGLES
 * GL_TRIANGLE_STRIP
 * GL_QUADS
 * GL_QUAD_STRIP
 *
 * Armazena também o center e triangles formados pelos points
 *
 * @author GlauKo
 */
public class BasicModel implements FormaTriangulos, DrawModel, ColorModel,
        Cloneable {

    protected Vetor3f[] points;
    protected ArrayList<Triangulo> triangles;
    protected Vetor3f center;
    protected int drawType;
    protected Color4f color;
    protected boolean visible;

    public BasicModel(Vetor3f center, Vetor3f[] points, int drawType) {
        this.center = center;
        this.points = points;
        setDrawType(drawType);
    }

    public void formaTriangulos() {
        triangles = new ArrayList<Triangulo>();
        if (points.length > 2) {
            switch (drawType) {
                case GL.GL_TRIANGLES:
                    formaTriangulosDeTriangles();
                    break;
                case GL.GL_TRIANGLE_STRIP:
                    formaTriangulosDeTriangles();
                    break;
                case GL.GL_QUADS:
                    formaTriangulosDeQuadrados();
                    break;
                case GL.GL_QUAD_STRIP:
                    formaTriangulosDeQuadrados();
                    break;
            }
        }
    }

    private void formaTriangulosDeTriangles() {
        for (int i = 2; i < points.length; i++) {
            triangles.add(new Triangulo(points[i - 2],
                    points[i - 1],
                    points[i]));
        }
    }

    private void formaTriangulosDeQuadrados() {
        for (int i = 3; i < points.length; i += 2) {
            triangles.add(new Triangulo(points[i - 3],
                    points[i - 2],
                    points[i - 1]));
            triangles.add(new Triangulo(points[i - 3],
                    points[i],
                    points[i - 1]));
        }
    }

    public void setColor(Color4f color) {
        this.color = color;
    }

    public Color4f getColor() {
        return color;
    }

    protected void setPoints(Vetor3f[] points) {
        this.points = points;
    }

    protected void setCenter(Vetor3f center) {
        this.center = center;
    }
    
    public float maxY(){
        float maxY = Float.NEGATIVE_INFINITY;
        for (Vetor3f v : points) {
            maxY = Math.max(v.getY(), maxY);
        }
        return maxY;
    }
    
    public float minY(){
        float minY = Float.POSITIVE_INFINITY;
        for (Vetor3f v : points) {
            minY = Math.min(v.getY(), minY);
        }
        return minY;
    }
    
    public float maxX(){
        float maxX = Float.NEGATIVE_INFINITY;
        for (Vetor3f v : points) {
            maxX = Math.max(v.getX(), maxX);
        }
        return maxX;
    }
    
    public float minX(){
        float minX = Float.POSITIVE_INFINITY;
        for (Vetor3f v : points) {
            minX = Math.min(v.getX(), minX);
        }
        return minX;
    }
    
    public void draw(GL gl) {
        if(visible){
            // Seta a cor da peça
            if (color != null) {
                float r = color.getR();
                float g = color.getG();
                float b = color.getB();
                float a = color.getAlpha();
                gl.glColor4f(r, g, b, a);
            } else {
                gl.glColor3d(Math.random(), Math.random(), Math.random());
            }

            // desenha a peça
            gl.glBegin(drawType);
            for (Vetor3f v : points) {
                gl.glVertex3f(v.getX(), v.getY(), v.getZ());
            }
            gl.glEnd();
        }
    }

    public void setDrawType(int type) {
        drawType = type;
    }

    public int getDrawType() {
        return drawType;
    }

    public Vetor3f getCenter() {
        return center;
    }
    
    public Vetor3f getPoint(String name){
        for(Vetor3f p : points){
            if(p.getName().equals(name))
                return p;
        }
        return null;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        BasicModel cloned = (BasicModel) super.clone();
        
        Vetor3f[] pts = new Vetor3f[points.length];
        for(int i = 0; i < points.length; i++){
            pts[i] = (Vetor3f) points[i].clone();
        }
        cloned.setPoints(pts);
        
        cloned.setCenter((Vetor3f) center.clone());
        
        cloned.setColor((Color4f)color.clone());
        
        cloned.formaTriangulos();
        
        return cloned;
    }
}
