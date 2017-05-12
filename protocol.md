### header ###
10 byte
1* id
1* kind
8(* password <client>)

### kind ###
0 ~ 255
S* talk(receive)  ... login status  (name comment)*
 ex)
    255
    0
    TFFTFTT
    [4]
    f*ck
    [5]
    IMG_ROLIROLI
    hogehoge.png
    [3]
    hello
    [255]
    JANKEN_JOIN_ROLIROLI
    3
    3 4 1
    [255]
    JANKEN_RESULT_ROLIROLI
    3 R 4 R 1 P
    1

    
* talk(send)      ... comment
 ex)
    <client>
        サンワツが「ワロた」と発言
    3
    1 (talkのkindは1)
    croisson(char[])
    ワロた


* login           ... token(LOGIN_ROLIROLI)
 ex)
    <client>
    サンワツログイン
  3
  3
  croisson
  LOGIN_ROLIROLI


* setting_name    ... name
* setting_pass    ... password
* setting_icon    ... image
* image(send)     ... image
* request_image   ... image_name
S* image(receive) ... image
S* responce       ... OK | NO
* janken_start(send)
                  ... (user_id)*
* janken_hand     ... R | S | P

