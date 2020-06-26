package test.r2.integ.helper;

import com.linkedin.r2.message.stream.entitystream.WriteHandle;

/**
 * @author Zhenkai Zhu
 */
public class TimedBytesWriter extends BytesWriter
{
  private long _startTime;
  private long _stopTime;
  private StringBuilder _sb = new StringBuilder();

  public String getLog()
  {
    return _sb.toString();
  }

  private long _total;

  public TimedBytesWriter(long total, byte fill)
  {
    super(total, fill);
    _total = total;
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
    long written = getWritten();
    if (written >= (_total - 10))
    {
    _sb.append(System.nanoTime()).append(" ").append("total=").append(_total).append(" written=").append(written).append("\n");
    }
    super.onWritePossible();
  }

  @Override
  protected void onFinish()
  {
    _sb.append(System.nanoTime()).append(" onFinish:").append("total=").append(_total).append(" written=").append(getWritten()).append("\n");
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
