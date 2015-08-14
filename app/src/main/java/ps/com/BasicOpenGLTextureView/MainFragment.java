package ps.com.BasicOpenGLTextureView;

import android.app.Fragment;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class MainFragment extends Fragment {
    public static MainFragment instance;
    private TextureView mTextureView;
    private RenderThread renderThread;
    private SurfaceTexture renderSurface;
    private FloatBuffer mVertices;
    private float[] mVerticesData;

    public MainFragment() {
        instance = this;
    }

    public void changeVertexData() {
        mVerticesData = new float[(3 + 4) * 3 * 1]; // 1 = dataset.length

        int ct = 0;
        mVerticesData[ct++] = 0.0f;
        mVerticesData[ct++] = 0.0f;
        mVerticesData[ct++] = 0.0f;
        mVerticesData[ct++] = 1.0f;
        mVerticesData[ct++] = 1.0f;
        mVerticesData[ct++] = 1.0f;
        mVerticesData[ct++] = 1.0f;

        mVerticesData[ct++] = 0.0f;
        mVerticesData[ct++] = 1.0f;
        mVerticesData[ct++] = 0.0f;
        mVerticesData[ct++] = 0.0f;
        mVerticesData[ct++] = 1.0f;
        mVerticesData[ct++] = 1.0f;
        mVerticesData[ct++] = 1.0f;

        mVerticesData[ct++] = 1.0f;
        mVerticesData[ct++] = 1.0f;
        mVerticesData[ct++] = 0.0f;
        mVerticesData[ct++] = 1.0f;
        mVerticesData[ct++] = 1.0f;
        mVerticesData[ct++] = 0.0f;
        mVerticesData[ct++] = 1.0f;


        mVertices = ByteBuffer.allocateDirect(mVerticesData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mVertices.put(mVerticesData).position(0);

        renderThread.setVertices(mVertices, mVerticesData);
        renderThread.start();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mTextureView = (TextureView) getView().findViewById(R.id.texture_view);
        mTextureView.setSurfaceTextureListener(new GLSurfaceTextureListener());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    private class GLSurfaceTextureListener implements
            TextureView.SurfaceTextureListener {

        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface,
                                              int width, int height) {
            renderThread = new RenderThread(surface, getActivity().getApplicationContext());
            renderSurface = surface;
            changeVertexData();
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface,
                                                int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        }

    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        renderThread = null;
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        renderThread = null;
//    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (renderSurface != null) {
//            renderThread = new RenderThread(renderSurface);
//            renderThread.start();
//        }
//    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        if (renderSurface != null) {
//            renderThread = new RenderThread(renderSurface);
//            renderThread.start();
//        }
//    }
}