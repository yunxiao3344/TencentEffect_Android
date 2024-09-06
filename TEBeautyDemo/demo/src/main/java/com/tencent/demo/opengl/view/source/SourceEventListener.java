package com.tencent.demo.opengl.view.source;

import com.tencent.demo.opengl.gles.EglCore;
import com.tencent.demo.opengl.view.FrameTexture;

// Through this interface, data generated by the source is returned to the caller.
public interface SourceEventListener {

    // Notify the listener when the GL environment is successfully created.
    void onGLContextCreated(EglCore eglCore);

    // Notify the listener in the GL environment when the SurfaceHolder of the SurfaceView in the Display is recreated.
    void onSurfaceRecreated(EglCore eglCore);

    // Used to return data generated by the Source.
    void onReceiveFrameTexture(EglCore eglCore, FrameTexture frameTexture);

    // Used to notify that the GL environment is about to be destroyed.
    void onGLContextDestroy();
}
