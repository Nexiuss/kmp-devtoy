package com.nexius.ui

import androidx.compose.ui.graphics.ImageBitmap
import com.nexius.devtoy.loadIcon

interface Icons {
   enum class Filled{
       Image,
       Edit,
       Favorite,
       Add,
       Info,
       Check,
       Share,
       ArrowBack,
       Camera,
       Star,
       Menu,
       ShoppingCart,
       EditCalendar,
       AccessTime,
       Mail,
       DirectionsWalk,
       Settings,
       CloudDownload,
       Done,
       Person,
       ;

       fun toVector(): ImageBitmap {
           return loadIcon(this.name.lowercase()+".png")
       }
   }
   enum class Outlined{
       Image,
       Edit,
       Send,
       Info,
       OpenInNew,
       Help,
       Feedback,
       Add,
       Check,
       Share,
       ArrowBack,
       Menu,
       ShoppingCart,
       Mail,
       Settings,
       Done,
       Person,
       ;

       fun toVector(): ImageBitmap {
           //name转小驼峰
           return loadIcon(this.name.lowercase()+".png")
       }
   }
    enum class Default{
        Close,
        Search,
        MoreVert,
        DirectionsRun,
        Delete,
        CheckBoxOutlineBlank,
        CheckBox,
        Tune,
        DirectionsBus,
        MusicNote,
        DirectionsCar,
        PlaylistAddCircle,
        ArrowDropDown,
        Album,
        Hiking,
        DirectionsWalk,
        DateRange,
        Info,
        DirectionsBike,
        Image,
        Add,
        Check,
        Share,
        ArrowBack,
        Menu,
        ShoppingCart,
        Mail,
        Settings,
        Done,
        Person,
        ;

        fun toVector(): ImageBitmap {
            //name转小驼峰
            return loadIcon(this.name.lowercase()+".png")
        }
    }

}

class R{
    class drawable {
        open var feathertop  = ""
    }

}
