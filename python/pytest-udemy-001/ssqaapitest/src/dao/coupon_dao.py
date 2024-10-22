
import logging as logger

from src.utilities.dbUtility import DBUtility

class CouponDAO(object):

    def __init__(self):
        self.db_helper = DBUtility()

    def get_coupon_with_filter(self, coupon_code):
        sql = "SELECT P.ID, P.post_title AS coupon_code, " \
            "Max(CASE WHEN PM.meta_key = 'discount_type' AND  P.ID = PM.post_id THEN PM.meta_value END)" \
            " AS discount_type, " \
            "Max(CASE WHEN PM.meta_key = 'coupon_amount' AND  P.ID = PM.post_id THEN PM.meta_value END)" \
            " AS coupon_amount " \
            "FROM wordpress.wp_posts AS P " \
            "JOIN wordpress.wp_postmeta AS PM " \
            "ON P.ID = PM.post_id " \
            "WHERE P.post_type = 'shop_coupon' AND P.post_status = 'publish' " \
            f"AND P.post_title = '{coupon_code}' " \
            "GROUP  BY P.ID ;"

        rs_sql = self.db_helper.execute_select(sql)

        return rs_sql