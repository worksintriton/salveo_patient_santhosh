package com.salveo.mysalveo.api;

import com.salveo.mysalveo.requestpojo.*;
import com.salveo.mysalveo.responsepojo.*;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RestApiInterface {

    /*splash screen list*/
    @GET("splashscreen/getlist")
    Call<SplashScreenResponse> splashScreenResponseCall(@Header("Content-Type") String type);

    /*user types list*/
    @GET("usertype/mobile/getlist")
    Call<UserTypeListResponse> userTypeListResponseCall(@Header("Content-Type") String type);

    /*family member relation type get list*/
    @GET("familymember/dropdown")
    Call<GetFamilyMemberResponse> getfamilymembersListResponseCall(@Header("Content-Type") String type);

    /*family member create*/
    @POST("familymember/create")
    Call<FamilyMemberCreateResponse> familymembercreateResponseCall(@Header("Content-Type") String type, @Body FamilyMemberCreateRequest familyMemberCreateRequest);

    /*family member list show*/
    @POST("familymember/getlist_by_userid")
    Call<FamilyMemberListResponse> familymembersListResponseCall(@Header("Content-Type") String type, @Body FamilyMemberListRequest familyMemberListRequest);

    /*family member edit*/
    @POST("familymember/edit")
    Call<FamilyMembersEditResponse> familymemberseditResponseCall(@Header("Content-Type") String type, @Body FamilyMembersEditRequest familyMembersEditRequest);

    /*Signup create*/
    @POST("userdetails/create")
    Call<SignupResponse> signupResponseCall(@Header("Content-Type") String type, @Body SignupRequest signupRequest);

    /*Email OTP */
    @POST("userdetails/send/emailotp")
    Call<EmailOTPResponse> emailOTPResponseCall(@Header("Content-Type") String type, @Body EmailOTPRequest emailOTPRequest);


    /*Profile update*/
    @POST("userdetails/mobile/update/profile")
    Call<ProfileUpdateResponse> profileUpdateResponseCall(@Header("Content-Type") String type, @Body ProfileUpdateRequest profileUpdateRequest);

    /*User Staus update*/
    @POST("userdetails/mobile/edit")
    Call<UserStatusUpdateResponse> userStatusUpdateResponse(@Header("Content-Type") String type, @Body UserStatusUpdateRequest userStatusUpdateRequest);

    /*Resend otp*/
    @POST("userdetails/mobile/resendotp")
    Call<ResendOTPResponse> resendOTPResponsecall(@Header("Content-Type") String type, @Body ResendOTPRequest resendOTPRequest);

    /*Login*/
    @POST("userdetails/mobile/login")
    Call<LoginResponse> loginResponseCall(@Header("Content-Type") String type, @Body LoginRequest loginRequest);

    /*Notification token update*/
    @POST("userdetails/mobile/update/fb_token")
    Call<FBTokenUpdateResponse> fBTokenUpdateResponseCall(@Header("Content-Type") String type, @Body FBTokenUpdateRequest fbTokenUpdateRequest);

    /*Pet Lover*/
    /*dropdown list*/
    @GET("petdetails/mobile/dropdownslist")
    Call<DropDownListResponse> dropDownListResponseCall(@Header("Content-Type") String type);

    /*Add your pet*/
    @POST("petdetails/mobile/create")
    Call<AddYourPetResponse> addYourPetResponseCall(@Header("Content-Type") String type, @Body AddYourPetRequest addYourPetRequest);

    /*Pet lover dashboard*/
    @POST("userdetails/petlove/mobile/dashboardtest")
    Call<PetLoverDashboardResponse> petLoverDashboardResponseCall(@Header("Content-Type") String type, @Body PetLoverDashboardRequest petLoverDashboardRequest);

    /*Add location*/
    @POST("locationdetails/create")
    Call<LocationAddResponse> locationAddResponseCall(@Header("Content-Type") String type, @Body LocationAddRequest locationAddRequest);

    /*Image upload*/
    @Multipart
    @POST("upload")
    Call<FileUploadResponse> getImageStroeResponse(@Part MultipartBody.Part file);

    /*Coupon code list*/
    @POST("coupon_code/getlist_id")
    Call<CouponCodeListResponse> CouponCodeListResponseCall(@Header("Content-Type") String type, @Body CouponCodeListRequest couponCodeListRequest);

    /*Listing all pets*/
    @GET("pettype/mobile/getlist")
    Call<PetTypeListResponse> petTypeListResponseCall(@Header("Content-Type") String type);

    /*Listing all health issues*/
    @GET("healthissue/getlist")
    Call<HealthIssuesListResponse> healthissueListResponseCall(@Header("Content-Type") String type);

    /*Listing all breed by Pet ID*/
    @POST("breedtype/mobile/getlist_id")
    Call<BreedTypeResponse> breedTypeResponseByPetIdCall(@Header("Content-Type") String type, @Body BreedTypeRequest breedTypeRequest);

    /*Pet Details List by User ID*/
    @POST("petdetails/mobile/getlist_id")
    Call<PetDetailsResponse> petDetailsResponseByUserIdCall(@Header("Content-Type") String type, @Body PetDetailsRequest petDetailsRequest);

    /*Pet Doctor available timeslot*/
    @POST("new_doctortime/get_doc_new1")
    Call<PetDoctorAvailableTimeResponse> petDoctorAvailableTimeResponseCall(@Header("Content-Type") String type, @Body PetDoctorAvailableTimeRequest petDoctorAvailableTimeRequest);

    /*Appointment check */
    @POST("appointments/check")
    Call<AppointmentCheckResponse> appointmentCheckResponseCall(@Header("Content-Type") String type, @Body AppointmentCheckRequest appointmentCheckRequest);

    /*Pet Appointment Create*/
    @POST("appointments/mobile/create")
    Call<PetAppointmentCreateResponse> petAppointmentCreateResponseCall(@Header("Content-Type") String type, @Body PetAppointmentCreateRequest petAppointmentCreateRequest);

    /*Pet Current Appointment*/
    @POST("appointments/mobile/plove_getlist/newapp1")
    Call<PetAppointmentResponse> petAppointmentResponseCall(@Header("Content-Type") String type, @Body PetLoverAppointmentRequest petLoverAppointmentRequest);


    /*Pet Current Appointment*/
    @POST("appointments/mobile/plove_getlist/newapp")
    Call<PetNewAppointmentResponse> petNewAppointmentResponseCall(@Header("Content-Type") String type, @Body PetLoverAppointmentRequest petLoverAppointmentRequest);

    /*Pet Completed Appointment*/
    @POST("appointments/mobile/plove_getlist/comapp1")
    Call<PetAppointmentResponse> petCompletedAppointmentResponseCall(@Header("Content-Type") String type, @Body PetLoverAppointmentRequest petLoverAppointmentRequest);

    /*Pet Cancelled Appointment*/
    @POST("appointments/mobile/plove_getlist/missapp1")
    Call<PetAppointmentResponse> petMissedAppointmentResponseCall(@Header("Content-Type") String type, @Body PetLoverAppointmentRequest petLoverAppointmentRequest);


    /*Listing Location by ID*/
    @POST("locationdetails/mobile/getlist_id")
    Call<LocationListAddressResponse> locationListAddressResponseCall(@Header("Content-Type") String type, @Body LocationListAddressRequest locationListAddressRequest);

    /*Pet Details List by User ID*/
    @POST("petdetails/mobile/getlist_id")
    Call<PetListResponse> petListResponseCall(@Header("Content-Type") String type, @Body PetListRequest petListRequest);

    /*update other information Petdetails Using User id*/
    @POST("petdetails/edit")
    Call<PetAddImageResponse> petUpdateOtherInformationResponseCall(@Header("Content-Type") String type, @Body PetUpdateOtherInformationRequest petUpdateOtherInformationRequest);


    /*update petimage Petdetails Using User id*/
    @POST("petdetails/edit")
    Call<PetAddImageResponse> PetAddImageResponseCall(@Header("Content-Type") String type, @Body PetAddImageRequest petAddImageRequest);

    /*Edit Petdetails Using User id*/
    @POST("petdetails/edit")
    Call<PetAddImageResponse> petUpdateResponseCall(@Header("Content-Type") String type, @Body PetEditRequest petEditRequest);

    /*Delete Petdetails Using User id*/
    @POST("familymember/mobile_delete")
    Call<PetDeleteResponse> petDeleteResponseCall(@Header("Content-Type") String type, @Body PetDeleteRequest petDeleteRequest);

    /*location list delete*/
    @POST("locationdetails/delete")
    Call<LocationDeleteResponse> locationDeleteResponseCall(@Header("Content-Type") String type, @Body LocationDeleteRequest locationDeleteRequest);

    /*Location status change*/
    @POST("locationdetails/default/edit")
    Call<LocationStatusChangeResponse> locationStatusChangeResponseCall(@Header("Content-Type") String type, @Body LocationStatusChangeRequest locationStatusChangeRequest);

    /*Location update*/
    @POST("locationdetails/edit")
    Call<LocationUpdateResponse> locationUpdateResponseCall(@Header("Content-Type") String type, @Body LocationUpdateRequest locationUpdateRequest);

    /*Pet lover review add*/
    @POST("appointments/reviews/update")
    Call<AddReviewResponse> addReviewResponseCall(@Header("Content-Type") String type, @Body AddReviewRequest addReviewRequest);

    /*SP lover review add*/
    @POST("sp_appointments/reviews/update")
    Call<AddReviewResponse> spaddReviewResponseCall(@Header("Content-Type") String type, @Body AddReviewRequest addReviewRequest);

    /*Shop review add*/
    @POST("product_details/reviews/update")
    Call<AddReviewResponse> shopaddReviewResponseCall(@Header("Content-Type") String type, @Body AddShopReviewRequest addShopReviewRequest);


    /*Specific service*/
    @POST("service_provider/mobile/servicedetails")
    Call<SPSpecificServiceDetailsResponse> SPSpecificServiceDetailsResponseCall(@Header("Content-Type") String type, @Body SPSpecificServiceDetailsRequest spSpecificServiceDetailsRequest);

    /*Service category list*/
    @POST("service_provider/mobile/service_cat")
    Call<ServiceCatResponse> ServiceCatResponseCall(@Header("Content-Type") String type, @Body ServiceCatRequest serviceCatRequest);

    /*Service Details*/
    @POST("service_provider/mobile/sp_fetch_by_id")
    Call<SPDetailsRepsonse> SPDetailsRepsonseCall(@Header("Content-Type") String type, @Body SPDetailsRequest spDetailsRequest);


    /*SP available timeslot*/
    @POST("sp_available_time/get_sp_new")
    Call<SPAvailableTimeResponse> spAvailableTimeResponseCall(@Header("Content-Type") String type, @Body PetDoctorAvailableTimeRequest petDoctorAvailableTimeRequest);


    /*Doctor*/
    /*Doctor Business info create*/
    @POST("doctordetails/create")
    Call<DocBusInfoUploadResponse> docsBusInfoUpldResponse(@Header("Content-Type") String type, @Body DocBusInfoUploadRequest docBusInfoUploadRequest);

    /*update doctor profile image Using User id*/
    @POST("userdetails/mobile/update/profile")
    Call<DoctorUpdateProfileImageResponse> DoctorUpdateProfileImageResponseCall(@Header("Content-Type") String type, @Body DoctorUpdateProfileImageRequest doctorUpdateProfileImageRequest);


    /*Doctor Business info getby id*/
    @POST("doctordetails/fetch_doctor_user_id")
    Call<DoctorDetailsByUserIdResponse> doctorDetailsByUserIdResponseCall(@Header("Content-Type") String type, @Body DoctorDetailsByUserIdRequest doctorDetailsByUserIdRequest);

    /*Doctor Business info update*/
    @POST("doctordetails/edit")
    Call<DocBusInfoUploadResponse> doctorBusinessInfoUpdateResponseCall(@Header("Content-Type") String type, @Body DoctorBusinessInfoUpdateRequest doctorBusinessInfoUpdateRequest);


    /*Doctor Create holiday*/
    @POST("holiday/create")
    Call<CreateHolidayResponse> createHolidayResponseCall(@Header("Content-Type") String type, @Body CreateHolidayRequest createHolidayRequest);

    /*Doctor Listing added holidays*/
    @POST("holiday/getlist_id")
    Call<HolidayListResponse> holidayListResponseCall(@Header("Content-Type") String type, @Body HolidayListRequest holidayListRequest);

    /*Doctor holiday delete*/
    @POST("holiday/delete")
    Call<HolidayDeleteResponse> holidayDeleteResponseCall(@Header("Content-Type") String type, @Body HolidayDeleteRequest holidayDeleteRequest);

    /*doctor my calendar available days*/
    @POST("new_doctortime/fetch_dates")
    Call<DoctorMyCalendarAvlDaysResponse> doctorMyCalendarAvlDaysResponseCall(@Header("Content-Type") String type, @Body DoctorMyCalendarAvlDaysRequest doctorMyCalendarAvlDaysRequest);

    /*doctor my calendar available times*/
    @POST("new_doctortime/get_time_Details")
    Call<DoctorMyCalendarAvlTimesResponse> doctorMyCalendarAvlTimesResponseCall(@Header("Content-Type") String type, @Body DoctorMyCalendarAvlTimesRequest doctorMyCalendarAvlTimesRequest);


    /*doctor my calendar update*/
    @POST("new_doctortime/update_doc_date")
    Call<DoctorMyCalendarUpdateDocDateResponse> doctorMyCalendarUpdateDocDateResponseCall(@Header("Content-Type") String type, @Body DoctorMyCalendarUpdateDocDateRequest doctorMyCalendarUpdateDocDateRequest);

    /*Doctor New Appointment*/
    @POST("appointments/mobile/doc_getlist/newapp")
    Call<DoctorAppointmentsResponse> doctorNewAppointmentResponseCall(@Header("Content-Type") String type, @Body DoctorNewAppointmentRequest doctorNewAppointmentRequest);

    /*Doctor Completed Appointment*/
    @POST("appointments/mobile/doc_getlist/comapp")
    Call<DoctorAppointmentsResponse> doctorCompletedAppointmentResponseCall(@Header("Content-Type") String type, @Body DoctorNewAppointmentRequest doctorNewAppointmentRequest);

    /*Doctor Missed Appointment*/
    @POST("appointments/mobile/doc_getlist/missapp")
    Call<DoctorAppointmentsResponse> doctorMissedAppointmentResponseCall(@Header("Content-Type") String type, @Body DoctorNewAppointmentRequest doctorNewAppointmentRequest);


    /*Doctor fetch details based on id*/
    @POST("doctordetails/fetch_doctor_id")
    Call<DoctorDetailsResponse> doctorDetailsResponseCall(@Header("Content-Type") String type, @Body DoctorDetailsRequest doctorDetailsRequest);


    /*Doctor Check status*/
    @POST("doctordetails/check_status")
    Call<DoctorCheckStatusResponse> doctorCheckStatusResponseCall(@Header("Content-Type") String type, @Body DoctorCheckStatusRequest doctorCheckStatusRequest);

    /*Prescriptoin Create*/
    @POST("prescription/create")
    Call<PrescriptionCreateResponse> prescriptionCreateRequestCall(@Header("Content-Type") String type, @Body PrescriptionCreateRequest prescriptionCreateRequest);



    /*Prescriptoin Details*/
    @POST("prescription/fetch_by_appointment_id")
    Call<PrescriptionFetchResponse> prescriptionDetailsResponseCall(@Header("Content-Type") String type, @Body PrescriptionDetailsRequest prescriptionDetailsRequest);

    /*Update Appointment Status complete*/
    @POST("appointments/edit")
    Call<AppoinmentCompleteResponse> appoinmentCompleteResponseCall(@Header("Content-Type") String type, @Body AppoinmentCompleteRequest appoinmentCompleteRequest);

    /*Update Appointment Status cancel*/
    @POST("appointments/edit")
    Call<AppoinmentCancelledResponse> appoinmentCancelledResponseCall(@Header("Content-Type") String type, @Body AppoinmentCancelledRequest appoinmentCancelledRequest);

    /*doctor appointment close conversation*/
    @POST("appointments/edit")
    Call<AppointmentsUpdateResponse> appoinmentcloseconversationResponseCall(@Header("Content-Type") String type, @Body AppoinmentCloseRequest appoinmentCloseRequest);

    /*doctor appointment pet no show*/
    @POST("appointments/edit")
    Call<AppointmentsUpdateResponse> appoinmentpetnoResponseCall(@Header("Content-Type") String type, @Body PetNoShowRequest petNoShowRequest);

    /*doctor appointment start appointment*/
    @POST("appointments/edit")
    Call<AppointmentsUpdateResponse> doctorStartAppointmentResponseCall(@Header("Content-Type") String type, @Body DoctorStartAppointmentRequest doctorStartAppointmentRequest);


    /*Doctor Name and specialization search*/
    @POST("doctordetails/text_search")
    Call<DoctorSearchResponse> doctorSearchResponseCall(@Header("Content-Type") String type, @Body DoctorSearchRequest doctorSearchRequest);

    /*Doctor specialization near by and review Filter api*/
    @POST("doctordetails/filter_doctor")
    Call<FilterDoctorResponse> filterDoctorResponseCall(@Header("Content-Type") String type, @Body FilterDoctorRequest filterDoctorRequest);




    /*Service Provider*/

    /*service provider services list*/
    @GET("service_provider/sp_dropdown")
    Call<SPServiceListResponse> SPServiceListResponseCall(@Header("Content-Type") String type);

    /*service provider filter price list*/
    @GET("service_provider/filter_price_list")
    Call<SPFilterPriceListResponse> SPFilterPriceListResponseCall(@Header("Content-Type") String type);

    /*Service provider register form create*/
    @POST("service_provider/create")
    Call<ServiceProviderRegisterFormCreateResponse> serviceProviderRegisterFormCreateResponseCall(@Header("Content-Type") String type, @Body ServiceProviderRegisterFormCreateRequest serviceProviderRegisterFormCreateRequest);

    /*Service provider details by id*/
    @POST("service_provider/getlist_id")
    Call<ServiceProviderRegisterFormCreateResponse> spDetailsReponseByUserIdCall(@Header("Content-Type") String type, @Body SPDetailsByUserIdRequest spDetailsByUserIdRequest);

    /*Service provider update*/
    @POST("service_provider/edit")
    Call<ServiceProviderRegisterFormCreateResponse> spUpdateReponseCall(@Header("Content-Type") String type, @Body ServiceProviderRegisterFormCreateRequest serviceProviderRegisterFormCreateRequest);


    /*SP Check status*/
    @POST("service_provider/check_status")
    Call<SPCheckStatusResponse> SPCheckStatusResponseCall(@Header("Content-Type") String type, @Body SPCheckStatusRequest spCheckStatusRequest);

    /*sp my calendar available days*/
    @POST("sp_available_time/fetch_dates")
    Call<DoctorMyCalendarAvlDaysResponse> spMyCalendarAvlDaysResponseCall(@Header("Content-Type") String type, @Body SPMyCalendarAvlDaysRequest spMyCalendarAvlDaysRequest);

    /*sp my calendar available times*/
    @POST("sp_available_time/get_time_Details")
    Call<DoctorMyCalendarAvlTimesResponse> spMyCalendarAvlTimesResponseCall(@Header("Content-Type") String type, @Body DoctorMyCalendarAvlTimesRequest doctorMyCalendarAvlTimesRequest);

    /*sp my calendar update*/
    @POST("sp_available_time/update_doc_date")
    Call<DoctorMyCalendarUpdateDocDateResponse> spMyCalendarUpdateDocDateResponseCall(@Header("Content-Type") String type, @Body DoctorMyCalendarUpdateDocDateRequest doctorMyCalendarUpdateDocDateRequest);


    /*SP Create holiday*/
    @POST("sp_holiday/create")
    Call<CreateHolidayResponse> spCreateHolidayResponseCall(@Header("Content-Type") String type, @Body CreateHolidayRequest createHolidayRequest);

    /*SP Listing added holidays*/
    @POST("sp_holiday/getlist_id")
    Call<HolidayListResponse> spHolidayListResponseCall(@Header("Content-Type") String type, @Body HolidayListRequest holidayListRequest);

    /*SP holiday delete*/
    @POST("sp_holiday/delete")
    Call<HolidayDeleteResponse> spHolidayDeleteResponseCall(@Header("Content-Type") String type, @Body HolidayDeleteRequest holidayDeleteRequest);

    /*SP Create Appointment*/
    @POST("sp_appointments/mobile/create")
    Call<SPCreateAppointmentResponse> SPCreateAppointmentResponseCall(@Header("Content-Type") String type, @Body SPCreateAppointmentRequest spCreateAppointmentRequest);

    /*SP New Appointment*/
    @POST("sp_appointments/mobile/sp_getlist/newapp")
    Call<SPAppointmentResponse> spNewAppointmentResponseCall(@Header("Content-Type") String type, @Body SPAppointmentRequest spAppointmentRequest);

    /*SP Completed Appointment*/
    @POST("sp_appointments/mobile/sp_getlist/comapp")
    Call<SPAppointmentResponse> spCompletedAppointmentResponseCall(@Header("Content-Type") String type, @Body SPAppointmentRequest spAppointmentRequest);

    /*SP Missed Appointment*/
    @POST("sp_appointments/mobile/sp_getlist/missapp")
    Call<SPAppointmentResponse> spMissedAppointmentResponseCall(@Header("Content-Type") String type, @Body SPAppointmentRequest spAppointmentRequest);

    /*Update Appointment Status complete*/
    @POST("sp_appointments/edit")
    Call<AppoinmentCompleteResponse> spappoinmentCompleteResponseCall(@Header("Content-Type") String type, @Body AppoinmentCompleteRequest appoinmentCompleteRequest);

    /*Update Appointment Status cancel*/
    @POST("sp_appointments/edit")
    Call<AppoinmentCancelledResponse> spappoinmentCancelledResponseCall(@Header("Content-Type") String type, @Body AppoinmentCancelledRequest appoinmentCancelledRequest);



    /*Vendor*/

    /*Service provider register form create*/
    @POST("product_vendor/create")
    Call<VendorRegisterFormCreateResponse> vendorRegisterFormCreateResponseCall(@Header("Content-Type") String type, @Body VendorRegisterFormCreateRequest vendorRegisterFormCreateRequest);

    /*Service provider register form update*/
    @POST("product_vendor/edit")
    Call<VendorRegisterFormCreateResponse> vendorRegisterFormUpdateResponseCall(@Header("Content-Type") String type, @Body VendorRegisterFormCreateRequest vendorRegisterFormCreateRequest);


    /*Vendor Check status*/
    @POST("product_vendor/check_status")
    Call<SPCheckStatusResponse> VendorCheckStatusResponseCall(@Header("Content-Type") String type, @Body SPCheckStatusRequest spCheckStatusRequest);

    /*Pet New Appointment Details*/
    @POST("appointments/mobile/fetch_appointment_id")
    Call<PetNewAppointmentDetailsResponse> petNewAppointDetailResponseCall(@Header("Content-Type") String type, @Body PetNewAppointmentDetailsRequest petNewAppointmentDetailsRequest);

    /*Pet New Copy Appointment Details*/
    @POST("appointments/mobile/fetch_appointment_id")
    Call<PetNewAppointmentDetailsResponseCopy> petNewCopyAppointDetailResponseCall(@Header("Content-Type") String type, @Body PetNewAppointmentDetailsRequest petNewAppointmentDetailsRequest);

    /* SP Appointment Details*/
    @POST("sp_appointments/mobile/fetch_appointment_id")
    Call<SPAppointmentDetailsResponse> spAppointmentDetailsResponse(@Header("Content-Type") String type, @Body AppointmentDetailsRequest appointmentDetailsRequest);

    /* SP Copy Appointment Details*/
    @POST("sp_appointments/mobile/fetch_appointment_id")
    Call<SPAppointmentDetailsCopyResponse> spAppointmentCopyDetailsResponse(@Header("Content-Type") String type, @Body AppointmentDetailsRequest appointmentDetailsRequest);


    /*notifications list*/
    @POST("notification/mobile/getlist_id")
    Call<NotificationGetlistResponse> notificationGetlistResponseCall(@Header("Content-Type") String type, @Body NotificationGetlistRequest notificationGetlistRequest);

    /*notifications send request*/
    @POST("notification/mobile/alert/notification")
    Call<NotificationSendResponse> notificationSendResponseCall(@Header("Content-Type") String type, @Body NotificationSendRequest notificationSendRequest);

    /*notifications spsend request*/
    @POST("notification/mobile/alert/sp_notification")
    Call<NotificationSendResponse> spnotificationSendResponseCall(@Header("Content-Type") String type, @Body SPNotificationSendRequest spNotificationSendRequest);

    /*Vendor Order*/
    @POST("order_details/getorder_list")
    Call<VendorOrderResponse> vendorOrderResponseCall(@Header("Content-Type") String type, @Body VendorOrderRequest vendorOrderRequest);

    /*shop dashboard list*/
    @POST("product_details/getproductdetails_list")
    Call<ShopDashboardResponse> shopDashboardResponseCall(@Header("Content-Type") String type, @Body ShopDashboardRequest ShopDashboardRequest);

    /*View Todays Deal Products see more*/
    @POST("product_details/today_deal")
    Call<TodayDealMoreResponse> todayDealMoreResponseCall(@Header("Content-Type") String type, @Body TodayDealMoreRequest todayDealMoreRequest);


    /*View the Product's by cat id see more*/
    @POST("product_details/fetch_product_by_cat")
    Call<FetctProductByCatResponse> fetctProductByCatResponseCall(@Header("Content-Type") String type, @Body FetctProductByCatRequest fetctProductByCatRequest);

    /*View Single Product by Id*/
    @POST("product_details/fetch_product_by_id")
    Call<FetchProductByIdResponse> fetch_product_by_id_ResponseCall(@Header("Content-Type") String type, @Body FetchByIdRequest fetchByIdRequest);

    /*Add product*/
    @POST("product_cart_detail/add_product")
    Call<SuccessResponse> add_product_ResponseCall(@Header("Content-Type") String type, @Body FetchByIdRequest fetchByIdRequest);

    /*Remove product*/
    @POST("product_cart_detail/remove_product")
    Call<SuccessResponse> remove_product_ResponseCall(@Header("Content-Type") String type, @Body FetchByIdRequest fetchByIdRequest);

    /*Remove Single product*/
    @POST("product_cart_detail/remove_single_products")
    Call<SuccessResponse> remove_single_products_ResponseCall(@Header("Content-Type") String type, @Body FetchByIdRequest fetchByIdRequest);

    /*Remove all product*/
    @POST("product_cart_detail/remove_overall_products")
    Call<SuccessResponse> remove_overall_products_ResponseCall(@Header("Content-Type") String type, @Body FetchByIdRequest fetchByIdRequest);

    /*Remove all product*/
    @POST("product_cart_detail/cart_add_product")
    Call<SuccessResponse> cart_add_product_ResponseCall(@Header("Content-Type") String type, @Body CartAddProductRequest cartAddProductRequest);


    /*Cart page fetch details*/
    @POST("product_cart_detail/fetch_cart_details_by_userid")
    Call<CartDetailsResponse> fetch_cart_details_by_userid_ResponseCall(@Header("Content-Type") String type, @Body FetchByIdRequest fetchByIdRequest);

    /*Vendor booking create*/
    @POST("vendor_order_booking/create1")
    Call<CartSuccessResponse> vendor_order_booking_create_ResponseCall(@Header("Content-Type") String type, @Body CartDetailsResponse cartDetailsResponse);


    /*Vendor booked view orders*/
    @POST("vendor_order_booking/get_order_details_user_id")
    Call<PetVendorOrderResponse> get_order_details_user_id_ResponseCall(@Header("Content-Type") String type, @Body PetVendorOrderRequest petVendorOrderRequest);


    /*Vendor views orders*/
    @POST("vendor_order_booking/get_order_details_vendor_id")
    Call<VendorNewOrderResponse> get_order_details_vendordid_ResponseCall(@Header("Content-Type") String type, @Body VendorNewOrderRequest vendorNewOrderRequest);

    /*Vendor accepts order*/
    @POST("vendor_order_booking/update_status_accept")
    Call<VendorConfirmsOrderResponse> vendor_order_booking_accept_ResponseCall(@Header("Content-Type") String type, @Body VendorConfirmsOrderRequest vendorConfirmsOrderRequest);


    /*Vendor dipatches order*/
    @POST("vendor_order_booking/update_status_dispatch")
    Call<VendorDispatchesOrderResponse> vendor_order_booking_dispatch_ResponseCall(@Header("Content-Type") String type, @Body VendorDispatchesOrderRequest vendorDispatchesOrderRequest);


    /*Vendor cancels order*/
    @POST("vendor_order_booking/update_status_vendor_cancel")
    Call<VendorCancelsOrderResponse> vendor_order_booking_cancels_ResponseCall(@Header("Content-Type") String type, @Body VendorCancelsOrderRequest vendorCancelsOrderRequest);


    /*Vendor fetches order details*/
    @POST("vendor_order_booking/fetch_order_details_id")
    Call<VendorFetchOrderDetailsResponse> vendor_order_booking_order_fetches_ResponseCall(@Header("Content-Type") String type, @Body VendorFetchOrderDetailsIdRequest vendorFetchOrderDetailsIdRequest);


    /*Vendor gets order ID*/
    @POST("product_vendor/getlist_id")
    Call<VendorGetsOrderIDResponse> vendor_gets_orderbyId_ResponseCall(@Header("Content-Type") String type, @Body VendorGetsOrderIdRequest vendorGetsOrderIdRequest);

    /*Vendor gets order ID*/
    @GET("vendor_order_booking/cancel_status")
    Call<VendorReasonListResponse> vendorReasonListResponseCall(@Header("Content-Type") String type);

    /*Order Details*/
    @POST("vendor_order_booking/fetch_order_details_id")
    Call<VendorOrderDetailsResponse> vendorOrderDetailsResponseCall(@Header("Content-Type") String type, @Body VendorOrderDetailsRequest vendorOrderDetailsRequest);

    /*Cancel the order*/
    @POST("vendor_order_booking/update_status_cancel")
    Call<SuccessResponse> update_status_cancelResponseCall(@Header("Content-Type") String type, @Body UpdateStatusCancelRequest updateStatusCancelRequest);

    /*Return the order*/
    @POST("vendor_order_booking/update_status_return")
    Call<SuccessResponse> update_status_returnResponseCall(@Header("Content-Type") String type, @Body UpdateStatusReturnRequest updateStatusReturnRequest);

    /*Manage products list*/
    @POST("product_details/mobile/getlist_from_vendor_id1")
    Call<ManageProductsListResponse> getlist_from_vendor_id_ResponseCall(@Header("Content-Type") String type, @Body ManageProductsListRequest manageProductsListRequest);

    /* Vendor Accepts Return order*/
    @POST("vendor_order_booking/update_status_return")
    Call<VendorAcceptsReturnOrderResponse> update_status_vendor_accept_returnResponseCall(@Header("Content-Type") String type, @Body VendorAcceptReturnOrderRequest vendorAcceptReturnOrderRequest);


    /* User Fetches Shipping Address By ID */
    @POST("shipping_address/fetch_by_userid1")
    Call<ShippingAddressFetchByUserIDResponse> fetch_shipp_addr_ResponseCall(@Header("Content-Type") String type, @Body ShippingAddressFetchByUserIDRequest shippingAddressFetchByUserIDRequest);

    /* User lists Shipping Address By ID */
    @POST("shipping_address/listing_address_by_userid")
    Call<ShippingAddressListingByUserIDResponse> list_shipp_addr_ResponseCall(@Header("Content-Type") String type, @Body ShippingAddressListingByUserIDRequest shippingAddressListingByUserIDRequest);

    /* User Creates Shipping Address */
    @POST("shipping_address/create")
    Call<ShippingAddressCreateResponse> creates_shipp_addr_ResponseCall(@Header("Content-Type") String type, @Body ShippingAddressCreateRequest shippingAddressCreateRequest);

    /* User Deletes Shipping Address */
    @POST("shipping_address/delete")
    Call<ShippingAddressDeleteResponse> delete_shipp_addr_ResponseCall(@Header("Content-Type") String type, @Body ShippingAddressDeleteRequest shippingAddressDeleteRequest);

    /* User Edits Shipping Address */
    @POST("shipping_address/edit")
    Call<ShippingAddressEditResponse> edit_shipp_addr_ResponseCall(@Header("Content-Type") String type, @Body ShippingAddressEditRequest shippingAddressEditRequest);

    /* User Marks Chossen Shipping Address as Last Used*/
    @POST("shipping_address/mark_used_address")
    Call<ShippingAddrMarkAsLastUsedResponse> mark_shipp_addr_ResponseCall(@Header("Content-Type") String type, @Body ShippingAddrMarkAsLastUsedRequest shippingAddrMarkAsLastUsedRequest);

    /* Calculate Single Discount*/
    @POST("product_details/cal_discount_single")
    Call<ApplySingleDiscountCalResponse> cal_discount_single_ResponseCall(@Header("Content-Type") String type, @Body ApplySingleDiscountCalRequest applySingleDiscountCalRequest);


    /* Single Discount Apply*/
    @POST("product_details/discount_single")
    Call<ApplySingleDiscountResponse> apply_sing_dis_ResponseCall(@Header("Content-Type") String type, @Body ApplySingleDiscountRequest applySingleDiscountRequest);

    /* Multi Discount Apply*/
    @POST("product_details/discount_multi")
    Call<ApplyMultiProdDiscountResponse> apply_multi_dis_ResponseCall(@Header("Content-Type") String type, @Body ApplyMultiProdDiscountRequest applyMultiProdDiscountRequest);


    /* Shop Dashboard products search*/
    @POST("product_details/text_search")
    Call<ProductSearchResponse> productSearchResponseCall(@Header("Content-Type") String type, @Body ProductSearchRequest productSearchRequest);

    /*Shop Dashboard products today deals see more search*/
    @POST("product_details/todaydeal_text_search")
    Call<ProductSearchResponse> todayDealSearchResponseCall(@Header("Content-Type") String type, @Body ProductSearchRequest productSearchRequest);


    /*Shop Dashboard products category see more search*/
    @POST("product_details/cat_text_search")
    Call<ProductSearchResponse> categorySearchResponseCall(@Header("Content-Type") String type, @Body ProductSearchRequest productSearchRequest);


    /* Shop Dashboard sort by*/
    @POST("product_details/sort")
    Call<ProductSearchResponse> productSortByResponseCall(@Header("Content-Type") String type, @Body ProductSortByRequest productSortByRequest);

    /* Shop product filter*/
    @POST("product_details/filter")
    Call<ProductSearchResponse> productFiltersResponseCall(@Header("Content-Type") String type, @Body ProductFiltersRequest productFiltersRequest);

    /*vendor order list*/
    @POST("vendor_order_group/get_grouped_order_by_vendor")
    Call<VendorOrderListResponse> get_grouped_order_by_vendor_ResponseCall(@Header("Content-Type") String type, @Body VendorOrderListRequest vendorOrderListRequest);

    /*petlover vendor order list*/
    @POST("petlover_order_group/get_grouped_order_by_petlover")
    Call<PetLoverVendorOrderListResponse> get_grouped_order_by_petlover_ResponseCall(@Header("Content-Type") String type, @Body PetLoverVendorOrderListRequest petLoverVendorOrderListRequest);

    /*petlover vendor order details*/
    @POST("petlover_order_group/get_product_list_by_petlover")
    Call<PetLoverVendorOrderDetailsResponse> get_product_list_by_petlover_ResponseCall(@Header("Content-Type") String type, @Body PetLoverVendorOrderDetailsRequest petLoverVendorOrderDetailsRequest);

    /*vendor order details*/
    @POST("vendor_order_group/get_product_list_by_vendor")
    Call<PetLoverVendorOrderDetailsResponse> get_product_list_by_vendor_ResponseCall(@Header("Content-Type") String type, @Body VendorOrderDetailsListRequest vendorOrderDetailsListRequest);

    /*vendor single product track order details*/
    @POST("vendor_order_group/fetch_single_product_detail")
    Call<TrackOrderDetailsResponse> vendor_fetch_single_product_detail_ResponseCall(@Header("Content-Type") String type, @Body VendorTrackOrderDetailsRequest vendorTrackOrderDetailsRequest);

    /*petlover single product track order details*/
    @POST("petlover_order_group/fetch_single_product_detail")
    Call<TrackOrderDetailsResponse>petlover_fetch_single_product_detail_ResponseCall(@Header("Content-Type") String type, @Body TrackOrderDetailsRequest trackOrderDetailsRequest);

    /*vendor order update the status confirm*/
    @POST("vendor_order_group/update_vendor_status1")
    Call<VendorOrderUpdateResponse>vendor_update_order_confirm_ResponseCall(@Header("Content-Type") String type, @Body VendorOrderUpdateRequest vendorOrderUpdateRequest);

    /*vendor order update the status dispatch*/
    @POST("vendor_order_group/update_vendor_status2")
    Call<VendorOrderUpdateResponse>vendor_update_order_dispatch_ResponseCall(@Header("Content-Type") String type, @Body VendorOrderUpdateDispatchRequest vendorOrderUpdateDispatchRequest);

    /*vendor order update the status reject*/
    @POST("vendor_order_group/update_vendor_status3")
    Call<VendorOrderUpdateResponse>vendor_update_order_reject_ResponseCall(@Header("Content-Type") String type, @Body VendorOrderUpdateRejectRequest vendorOrderUpdateRequest);

   /*pet lover vendor order update the status reject*/
    @POST("vendor_order_group/update_vendor_status4")
    Call<VendorOrderUpdateResponse>petlover_update_order_cancel_ResponseCall(@Header("Content-Type") String type, @Body PetLoverCancelOrderRequest petLoverCancelOrderRequest);

    /*pet lover vendor order single cancel order*/
    @POST("vendor_order_group/update_vendor_status5")
    Call<VendorOrderUpdateResponse>petlover_update_order_cancel_single_ResponseCall(@Header("Content-Type") String type, @Body PetLoverCancelSingleOrderRequest petLoverCancelSingleOrderRequest);

    /*pet lover my orders review and update*/
    @POST("vendor_order_group/update_rating_review")
    Call<VendorOrderUpdateResponse>petlover_myorders_reviewandupdate_ResponseCall(@Header("Content-Type") String type, @Body PetLoverMyOrdersReviewandUpdateRequest petLoverMyOrdersReviewandUpdateRequest);

    /*Clear Today Deals*/
    @POST("product_details/mark_deal")
    Call<VendorOrderUpdateResponse>clearTodayDealsResponseCall(@Header("Content-Type") String type, @Body TodayDealsClearRequest todayDealsClearRequest);

    /*Product details edit*/
    @POST("product_details/mobile/edit_product")
    Call<VendorOrderUpdateResponse>productEditResponseCall(@Header("Content-Type") String type, @Body ProductEditRequest productEditRequest);


    /*Appointment reschedule API*/
    @POST("appointments/reshedule_appointment")
    Call<SuccessResponse>rescheduleResponseCall(@Header("Content-Type") String type, @Body RescheduleAppointmentRequest rescheduleAppointmentRequest);

   /*Appointments medical history*/
    @POST("appointments/medical_history")
    Call<MedicalHistoryResponse>medicalHistoryResponseCall(@Header("Content-Type") String type, @Body MedicalHistoryRequest medicalHistoryRequest);


    /*Doctor Products Fav List*/
    @POST("product_fav/getlist_id")
    Call<DoctorProductFavListResponse>doctorProductFavListResponseCall(@Header("Content-Type") String type, @Body DoctorProductFavListRequest doctorProductFavListRequest);


    /*Doctor Products Fav list create*/
    @POST("product_fav/create")
    Call<SuccessResponse>doctorProductFavListCreateResponseCall(@Header("Content-Type") String type, @Body DoctorProductFavListCreateRequest doctorProductFavListCreateRequest);



    /*Petlover Doctor Fav list*/
    @POST("doctor_fav/getlist_id")
    Call<FetchPetloverDoctorFavListResponse>petloverdoctorfavlistResponseCall(@Header("Content-Type") String type, @Body FetchPetloverFavListRequest fetchPetloverFavListRequest);

    /*Petlover SP Fav list*/
    @POST("sp_fav/getlist_id")
    Call<FetchPetloverSPFavListResponse>petloverspfavlistResponseCall(@Header("Content-Type") String type, @Body FetchPetloverFavListRequest fetchPetloverFavListRequest);

    /*Petlover Shop Fav list*/
    @POST("product_fav/getlist_id")
    Call<FetchPetloverShopFavListResponse>petlovershopfavlistResponseCall(@Header("Content-Type") String type, @Body FetchPetloverFavListRequest fetchPetloverFavListRequest);

    /*Create SP Fav list*/
    @POST("sp_fav/create")
    Call<SPFavCreateResponse>createspfavlistResponseCall(@Header("Content-Type") String type, @Body SPFavCreateRequest spFavCreateRequest);

    /*Create Shop Fav list*/
    @POST("product_fav/create")
    Call<ProductFavCreateResponse>createshopfavlistResponseCall(@Header("Content-Type") String type, @Body ProductFavCreateRequest productFavCreateRequest);

    /*Create Doctor Fav list*/
    @POST("doctor_fav/create")
    Call<SuccessResponse>createDoctorFavListResponseCall(@Header("Content-Type") String type, @Body DoctorFavCreateRequest doctorFavCreateRequest);

    /*Fetch Petlover Product Details*/
    @POST("appointments/doctor/petlover_payment")
    Call<FetchPetloverPaymDetaResponse>fetchpetlvrprodetaillistResponseCall(@Header("Content-Type") String type, @Body FetchPetloverPaymDetaRequest fetchPetloverPaymDetaRequest);

    /*Fetch Doctor Product Details*/
    @POST("appointments/doctor_payment")
    Call<FetchDoctorPaymDetaResponse>fetchdrprodetaillistResponseCall(@Header("Content-Type") String type, @Body FetchDoctorPaymDetaRequest fetchDoctorPaymDetaRequest);

    /*Fetch Petlover Payment Details*/
    @POST("appointments/doctor/petlover_payment")
    Call<FetchPetloverPaymDetaResponse>fetchpetlvrpaymdetaillistResponseCall(@Header("Content-Type") String type, @Body FetchPetloverPaymDetaRequest fetchPetloverPaymDetaRequest);

    /*Fetch Doctor Payment Details*/
    @POST("appointments/doctor_payment")
    Call<FetchDoctorPaymDetaResponse>fetchdrpaymetaillistResponseCall(@Header("Content-Type") String type, @Body FetchDoctorPaymDetaRequest fetchDoctorPaymDetaRequest);


    /*Listing all diagnosis*/
    @GET("diagnosis/getlist")
    Call<DiagnosisListResponse> diagnosisListResponseCall(@Header("Content-Type") String type);

    /*Listing all diagnosis by sub diagnosis*/
    @POST("sub_diagnosis/getlist_id")
    Call<SubDiagnosisListResponse> subDiagnosisListResponseCall(@Header("Content-Type") String type, @Body SubDiagnosisRequest subDiagnosisRequest);


    /*Vendor Add products*/
    /*category list*/
    @GET("product_cate/getlist_cat")
    Call<CatgoryGetListResponse> getlistCatResponseCall(@Header("Content-Type") String type);

    /*View the Product's by cat id see more*/
    @POST("newproduct_detail/fetch_product_by_cat")
    Call<FetctProductByCatDetailsResponse> fetctProductByCatDetailsResponse(@Header("Content-Type") String type, @Body FetctProductByCatRequest fetctProductByCatRequest);


    /*Vendor product update*/
    @POST("newproduct_detail/vendor_product_create")
    Call<SuccessResponse> vendor_product_create_ResponseCall(@Header("Content-Type") String type, @Body ProductVendorCreateRequest productVendorCreateRequest);



    /*communitym text list*/
    @GET("userdetails/community_text")
    Call<CommunityTextResponse> getcommunitytextResponseCall(@Header("Content-Type") String type);


    /*sos list*/
    @POST("sos_pet/create")
    Call<SOSListResponse> SOSListResponseCall(@Header("Content-Type") String type, @Body SOSListRequest sosListRequest);

    /*sos update*/
    @POST("sos_pet/edit")
    Call<SuccessResponse> SOSUpdateResponseCall(@Header("Content-Type") String type, @Body SOSUpdateRequest sosUpdateRequest);

    /*notification mark*/
    @POST("notification/mark_readed")
    Call<SuccessResponse> notificationMarkResponseCall(@Header("Content-Type") String type, @Body NotificationsMarkRequest notificationsMarkRequest);

    /*notification and cart count*/
    @POST("product_cart_detail/getlist_count")
    Call<NotificationCartCountResponse> notificationandCartCountResponseCall(@Header("Content-Type") String type, @Body NotificationCartCountRequest notificationCartCountRequest);

    /*CouponCodeCheckResponseCall*/
    @POST("coupon_code/check_coupon")
    Call<CouponCodeCheckResponse> CouponCodeCheckResponseCall(@Header("Content-Type") String type, @Body CouponCodeCheckRequest couponCodeCheckRequest);


}
