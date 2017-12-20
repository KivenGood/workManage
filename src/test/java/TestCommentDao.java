/**
 * Created by doter on 2017/7/27.
 *//*


import com.wlxk.Dao.AnswerDao;
import com.wlxk.Dao.CommentDao;
import com.wlxk.Pojo.Answer;
import com.wlxk.Pojo.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

*/
/**
 * \* Created with IntelliJ IDEA.
 * \* User: doter
 * \* Date: 2017/7/27
 * \* Time: 13:24
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 *//*

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:springConfig.xml"})
public class TestCommentDao {
    @Resource
    CommentDao commentDao;
    @Resource
    AnswerDao answerDao;

    @Test
    public void getBypid(){
       List<Comment> list = commentDao.getCommentByPid(1);
        for (Comment c:list) {
            System.out.println("A======="+c.getData());
        }

    }
    @Test
    public void getAnswer(){
        Answer answer = new Answer();
        answer.setUid(1);
        answer.setAnswer(2);
        answer.setTid(5);
        answer.setTtid(1);
        answer.setStartedDate(new Timestamp(new Date().getTime()));
        int a=answerDao.insertAnswer(answer);
        System.out.println("aaaa===="+answer.getId());
    }
}
*/
