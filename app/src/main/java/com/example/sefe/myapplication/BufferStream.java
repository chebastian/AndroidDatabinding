package com.example.sefe.myapplication;


/**
 * Created by sefe on 2017-02-08.
 */
public class BufferStream implements IBufferLoadCompleteListener {

    @Override
    public void onComplete() {
        isLoading = false;
        currentIndex = nextIndex;
    }

    public interface IBufferLoader
    {
        void reloadBufferAtWithSize(int start, int length, IBufferLoadCompleteListener onComplete);
    }

    private IBufferLoader _listener;
    private float bufferLoadPercent;
    private int currentIndex;
    private int bufferSize;
    private int nextIndex;
    private boolean isLoading;

    public BufferStream(IBufferLoader listener)
    {
        _listener = listener;
    }

    public void setBufferLoadAtPercent(float bufferLoadOffset) {
        this.bufferLoadPercent = bufferLoadOffset;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public boolean needsLoad(int index) {
        return index < BufferMinIndex() || index > BufferMaxIndex();
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void setCurrentReadingIndex(int index) {
        if(needsLoad(index) && !isLoading)
        {
            nextIndex = index;
            int newStart = BufferMinIndex(nextIndex);
            _listener.reloadBufferAtWithSize(newStart,bufferSize, this);
        }
    }

    private int BufferMinIndex(int index) {
        return (int) Math.max(index - (bufferSize*bufferLoadPercent),0);
    }

    private int BufferMaxIndex(int index) {
        return (int) Math.min(index + (bufferSize *bufferLoadPercent),currentIndex + bufferSize);
    }

    private int BufferMinIndex() {
        return BufferMinIndex(currentIndex);
    }

    private int BufferMaxIndex() {
        return BufferMaxIndex(currentIndex);
    }

    public int CurrentMidpoint()
    {
        return currentIndex;
    }

}
