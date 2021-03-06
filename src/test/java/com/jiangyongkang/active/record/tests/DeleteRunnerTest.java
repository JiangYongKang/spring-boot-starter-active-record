package com.jiangyongkang.active.record.tests;

import com.jiangyongkang.active.record.core.ActiveRecord;
import com.jiangyongkang.active.record.core.builder.DeleteBuilder;
import com.jiangyongkang.active.record.tests.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * author: vincent
 * date: 2019-02-13 16:49
 * comment:
 */

@Rollback
@Transactional
public class DeleteRunnerTest extends ActiveRecordApplicationTest {

    @Test
    public void deleteTest() {
        User user = ActiveRecord.first(User.class);
        boolean isDeleted = user.delete();
        Assert.assertTrue(isDeleted);
    }

    @Test
    public void deleteByIdTest() {
        boolean isDeleted = ActiveRecord.deleteById(User.class, 1);
        Assert.assertTrue(isDeleted);
    }

    @Test
    public void deleteBySQLTest() {
        boolean isDeleted = ActiveRecord.deleteBySQL(User.class, "name = ?", "nick");
        Assert.assertTrue(isDeleted);
    }

    @Test
    public void builderTest() {
        int deleteRow = new DeleteBuilder<>(User.class)
                .where("id = ?", 1)
                .where("name = ?", "vincent")
                .doIt();
        Assert.assertEquals(deleteRow, 1);
    }


}
