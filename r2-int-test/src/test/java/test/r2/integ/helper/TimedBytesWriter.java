package test.r2.integ.helper;

import com.linkedin.r2.message.stream.entitystream.WriteHandle;

/**
 * @author Zhenkai Zhu
 */
public class TimedBytesWriter extends BytesWriter
{
  private long _startTime;
  private volatile long _stopTime;

  public TimedBytesWriter(long total, byte fill)
  {
    super(total, fill);
  }

  @Override
  public void onInit(WriteHandle wh)
  {
    _startTime = System.currentTimeMillis();
    super.onInit(wh);
  }

  @Override
  public void onWritePossible()
  {
    super.onWritePossible();
  }

  @Override
  protected void onFinish()
  {
    _stopTime = System.currentTimeMillis();
    super.onFinish();
  }

  public long getStartTime()
  {
    return _startTime;
  }

  public long getStopTime()
  {
    return _stopTime;
  }
}
