package com.team.engine.rendering;

import static com.team.engine.Globals.*;

import com.team.engine.Engine;
import com.team.engine.vecmath.Vec2;

public class FontRenderer {
	public static void draw(float x, float y, int pSize, String text) {
		float size = pSize * 0.05f;
		
		ModelBuilder mb = new ModelBuilder();
		for (int i = 0; i < text.length(); i++) {
			Vec2 vertex_up_left = vec2(x + i * size/2, y + size);
		    Vec2 vertex_up_right = vec2(x + i * size/2 + size/2, y + size);
		    Vec2 vertex_down_right = vec2(x + i * size/2 + size/2, y);
		    Vec2 vertex_down_left = vec2(x + i * size/2, y);
		    
		    char character = text.charAt(i);
		    float uv_x = (character%16)/16.0f;
		    float uv_y = (character/16)/16.0f;
		    
		    //print(uv_x + ", " + uv_y);
		    
		    Vec2 uv_up_left = vec2(uv_x, uv_y);
		    Vec2 uv_up_right = vec2(uv_x + 1.0f / 16.0f, uv_y);
		    Vec2 uv_down_right = vec2(uv_x + 1.0f / 16.0f, (uv_y + 1.0f / 16.0f));
		    Vec2 uv_down_left = vec2(uv_x, (uv_y + 1.0f/16.0f));
		    
		    mb.vertex(vertex_up_left.x, vertex_up_left.y, -1, uv_up_left.x, uv_up_left.y);
		    mb.vertex(vertex_down_left.x, vertex_down_left.y, -1, uv_down_left.x, uv_down_left.y);
		    mb.vertex(vertex_up_right.x, vertex_up_right.y, -1, uv_up_right.x, uv_up_right.y);
		    
		    mb.vertex(vertex_down_right.x, vertex_down_right.y, -1, uv_down_right.x, uv_down_right.y);
		    mb.vertex(vertex_up_right.x, vertex_up_right.y, -1, uv_up_right.x, uv_up_right.y);
		    mb.vertex(vertex_down_left.x, vertex_down_left.y, -1, uv_down_left.x, uv_down_left.y);
		}
		
		
		Shader s = Engine.getShader("sprite");
		Engine.getTexture("ascii.png").bind();
		s.bind();
		s.uniformMat4("model", mat4());//.scale(vec3((float)Graphics.WINDOW_WIDTH, (float)Graphics.WINDOW_HEIGHT, 1f)));
		s.uniformMat4("view", mat4());
		s.uniformMat4("projection", mat4());
		s.uniformVec3("color", vec3(0.1f, 0.1f, 0.5f));
		//Engine.spriteMesh.draw();
		Mesh m = mb.toMesh();
		m.draw();
		m.delete();
	}
}

