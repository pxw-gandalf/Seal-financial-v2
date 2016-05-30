package cn.knet.seal.financial;

import org.junit.Test;

import cn.knet.seal.financial.bean.UploadTask;
import cn.knet.seal.financial.db.UploadDBHelper;
import cn.knet.seal.financial.global.KnetConstants;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    private UploadDBHelper mUploadHelper;

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void insertData() throws Exception{
        mUploadHelper = new UploadDBHelper(KnetFinancialApplication.context(), KnetConstants.DB_NAME);
        UploadTask uploadTask = new UploadTask("testFilename","testFilePath","testFileName","testTitle","testNail");
        mUploadHelper.insert(uploadTask);
    }

}