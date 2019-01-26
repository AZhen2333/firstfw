package net.cnitr.firstfw.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * shiro工具类
 */
public class ShiroKit {

    private static final String NAMES_DELIMETER = ",";
    public static final String NAMES_DELIMETER1 = NAMES_DELIMETER;

    /**
     * 加盐参数
     */
    public final static String hashAlgorithmName = "MD5";

    /**
     * 循环次数
     */
    public final static int hashIterations = 1024;

    /**
     * shiro密码加密工具类
     *
     * @param credentials 密码
     * @param saltSource  密码盐
     * @return
     */
    public static String md5(String credentials, String saltSource) {
        Md5Hash salt = new Md5Hash(saltSource);
        return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toString();
    }

    /**
     * 获取随机盐值
     *
     * @param length
     * @return
     */
    public static String getRandomSalt(int length) {
        return getRandomString(length);
    }

    /**
     * 获取当前subjecty对象
     *
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取封装的 ShiroUser
     *
     * @return
     */
    public static ShiroUser getUser() {
        return isGuest() ? null : (ShiroUser) getSubject().getPrincipals().getPrimaryPrincipal();
    }

    /**
     * 认证通过或以及记住的用户，与guset搭配使用
     *
     * @return
     */
    public static boolean isUser() {
        return getSubject() != null && getSubject().getPrincipal() != null;
    }

    /**
     * 验证当前用户是否为“访客”，即未认证（包含未记住）的用户。用user搭配使用
     *
     * @return 访客：true，否则false
     */
    public static boolean isGuest() {
        return !isUser();
    }

    /**
     * 从subject中获取session
     *
     * @return
     */
    public static Session getSession() {
        return getSubject().getSession();
    }

    /**
     * 获取shiro指定的sessionKey
     *
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getSession(String key) {
        Session session = getSession();
        return session == null ? null : (T) session.getAttribute(key);

    }

    /**
     * 设置shiro指定的sessionKey
     *
     * @param key
     * @param value
     */
    public static void setSessionAttr(String key, Object value) {
        Session session = getSession();
        session.setAttribute(key, value);
    }

    /**
     * 获取shiro指定的sessionKey
     *
     * @param key
     */
    public static void removeSessionAttr(String key) {
        Session session = getSession();
        if (session != null) {
            session.removeAttribute(key);
        }
    }

    /**
     * 验证当前用户是否属于该角色
     *
     * @param roleName
     * @return
     */
    public static boolean hasRole(String roleName) {
        return getSubject() != null
                && roleName != null
                && roleName.length() > 0
                && getSubject().hasRole(roleName);
    }

    /**
     * 与hasRole标签逻辑相反，当用户不属于该角色时验证通过。
     *
     * @param roleName
     * @return
     */
    public static boolean lacksRole(String roleName) {
        return !hasRole(roleName);
    }

    /**
     * 验证当前永辉是否属于以下任意一个角色
     *
     * @param roleNames 角色列表
     * @return
     */
    public static boolean hasAnyRole(String roleNames) {
        boolean hasAnyRole = false;
        Subject subject = getSubject();
        if (subject != null && roleNames != null && roleNames.length() > 0) {
            for (String role : roleNames.split(NAMES_DELIMETER)) {
                if (subject.hasRole(role.trim())) {
                    hasAnyRole = true;
                    break;
                }
            }
        }
        return hasAnyRole;
    }

    /**
     * 验证当前用户是否属于以下所有角色。
     *
     * @param roleNames 角色列表
     * @return
     */
    public static boolean hasAllRoles(String roleNames) {
        boolean hasAllRole = true;
        Subject subject = getSubject();
        if (subject != null && roleNames != null && roleNames.length() > 0) {
            for (String role : roleNames.split(NAMES_DELIMETER)) {
                if (!subject.hasRole(role.trim())) {
                    hasAllRole = false;
                    break;
                }
            }
        }
        return hasAllRole;
    }

    /**
     * 验证当前用户是否拥有指定权限,使用时与lacksPermission 搭配使用
     *
     * @param permission
     * @return
     */
    public static boolean hasPermission(String permission) {
        return getSubject() != null && permission != null
                && permission.length() > 0
                && getSubject().isPermitted(permission);
    }

    /**
     * 与hasPermission标签逻辑相反，当前用户没有制定权限时，验证通过。
     *
     * @param permission 权限名
     * @return 拥有权限：true，否则false
     */
    public static boolean lacksPermission(String permission) {
        return !hasPermission(permission);
    }

//    public static boolean isAdmin() {
//        List<Integer> roleList = ShiroKit.getUser().getRoleList();
//        for (Integer integer : roleList) {
//            String singleRoleTip = ConstantFactory.me().getSingleRoleTip(integer);
//            if (singleRoleTip.equals(Const.ADMIN_NAME)) {
//                return true;
//            }
//        }
//        return false;
//    }

    private static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
