package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model;

/**
 * Link Items to Coupons class is used in conjunction with the LINK_ITEM_COUPON_TABLE in the DBHelper so that
 * the user has access to call the coupons that an item is associated with.
 * @author Clarissa
 * @version 5/23/19
 */
public class LinkItemsToCoupons
{
    private String mItemID;
    private String mCouponID;

    public LinkItemsToCoupons(String itemID, String couponID) {
        mItemID = itemID;
        mCouponID = couponID;
    }

    public String getItemID() {
        return mItemID;
    }

    public void setItemID(String itemID) {
        mItemID = itemID;
    }

    public String getCouponID() {
        return mCouponID;
    }

    public void setCouponID(String couponID) {
        mCouponID = couponID;
    }

    @Override
    public String toString() {
        return "LinkItemsToCoupons{" +
                "mItemID='" + mItemID + '\'' +
                ", mCouponID='" + mCouponID + '\'' +
                '}';
    }
}
