package com.kav.wearoscarconnect.tiles;

import static androidx.wear.tiles.DimensionBuilders.dp;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.wear.tiles.*;
import androidx.wear.tiles.LayoutElementBuilders;
import androidx.wear.tiles.RequestBuilders;
import androidx.wear.tiles.ResourceBuilders;
import androidx.wear.tiles.TileBuilders;
import androidx.wear.tiles.TileService;
import androidx.wear.tiles.TimelineBuilders;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.kav.wearoscarconnect.R;

public class ActionTileService extends TileService {
    private static final String RESOURCES_VERSION = "1";

    //Clickables
    private final String ID_CLICK_ENGINE = "engineStartButton";
    private final String ID_CLICK_UNLOCK = "unlockButton";
    private final String ID_CLICK_LOCK = "lockButton";

    //Images
    private final String ID_IMAGE_ENGINE = "ic_engine";
    private final String ID_IMAGE_UNLOCK = "ic_unlock";
    private final String ID_IMAGE_LOCK = "ic_lock";

    @NonNull
    @Override
    protected ListenableFuture<TileBuilders.Tile> onTileRequest(@NonNull RequestBuilders.TileRequest requestParams) {
        LayoutElementBuilders.LayoutElement rootLayout = layout();

        //IF ANY BUTTON WERE CLICKED, DO THE ACTION
        //I don't want the TileAction to be instantiated if not needed
        switch (requestParams.getState().getLastClickableId()) {
            case ID_CLICK_ENGINE:
                TileAction tileAction = new TileAction(this);
                tileAction.startCarEngine();
                rootLayout = layoutWithMessage("Engine start sent");
                break;
            case ID_CLICK_UNLOCK:
                TileAction tileActionUnlock = new TileAction(this);
                tileActionUnlock.unlockCar();
                rootLayout = layoutWithMessage("unlock car sent");
                break;
            case ID_CLICK_LOCK:
                TileAction tileActionLock = new TileAction(this);
                tileActionLock.lockCar();
                rootLayout = layoutWithMessage("lock car sent");
                break;
        }

        TileBuilders.Tile tile = new TileBuilders.Tile.Builder()
                .setResourcesVersion(RESOURCES_VERSION)
                .setTimeline(
                        new TimelineBuilders.Timeline.Builder()
                                .addTimelineEntry(new TimelineBuilders.TimelineEntry.Builder()
                                        .setLayout(new LayoutElementBuilders.Layout.Builder()
                                                .setRoot(rootLayout
                                                ).build()
                                        ).build()
                                ).build()
                ).build();
        return Futures.immediateFuture(tile);
    }

    @NonNull
    @Override
    protected ListenableFuture<ResourceBuilders.Resources> onResourcesRequest(@NonNull RequestBuilders.ResourcesRequest requestParams) {
        return Futures.immediateFuture(new ResourceBuilders.Resources.Builder()
                .setVersion(RESOURCES_VERSION)
                .addIdToImageMapping( //We have to add the drawable icon here for when it is used in ontilerequest
                        ID_IMAGE_ENGINE,
                        new ResourceBuilders.ImageResource.Builder()
                                .setAndroidResourceByResId(
                                        new ResourceBuilders.AndroidImageResourceByResId.Builder()
                                                .setResourceId(R.drawable.ic_engine)
                                                .build()
                                ).build()
                )
                .addIdToImageMapping( //We have to add the drawable icon here for when it is used in ontilerequest
                        ID_IMAGE_UNLOCK,
                        new ResourceBuilders.ImageResource.Builder()
                                .setAndroidResourceByResId(
                                        new ResourceBuilders.AndroidImageResourceByResId.Builder()
                                                .setResourceId(R.drawable.ic_unlockedlock)
                                                .build()
                                ).build()
                )
                .addIdToImageMapping( //We have to add the drawable icon here for when it is used in ontilerequest
                        ID_IMAGE_LOCK,
                        new ResourceBuilders.ImageResource.Builder()
                                .setAndroidResourceByResId(
                                        new ResourceBuilders.AndroidImageResourceByResId.Builder()
                                                .setResourceId(R.drawable.ic_closedlock)
                                                .build()
                                ).build()
                )
                .build()
        );
    }

    /**
     * Add text to tile if action is started
     * @param message the message to display over buttons
     * @return the layout for the tile root
     */
    private LayoutElementBuilders.LayoutElement layoutWithMessage(String message) {
        return new LayoutElementBuilders.Column.Builder()
                .addContent(
                        new LayoutElementBuilders.Text.Builder()
                                .setText(message).build()
                )
                .addContent(
                        new LayoutElementBuilders.Spacer.Builder().setHeight(dp(8f)).build()
                )
                .addContent(
                        layout()
                ).build();
    }

    /**
     * build the layout of the tile with the help of columns and rows
     *
     * @return
     */
    private LayoutElementBuilders.LayoutElement layout() {
        //Build the
        return new LayoutElementBuilders.Column.Builder()
                .addContent(
                        new LayoutElementBuilders.Row.Builder()
                                .addContent(buttonWithImage(ID_CLICK_UNLOCK, ID_IMAGE_UNLOCK))
                                .addContent(new LayoutElementBuilders.Spacer.Builder().setWidth(dp(8f)).build())
                                .addContent(buttonWithImage(ID_CLICK_ENGINE, ID_IMAGE_ENGINE))
                                .addContent(new LayoutElementBuilders.Spacer.Builder().setWidth(dp(8f)).build())
                                .addContent(buttonWithImage(ID_CLICK_LOCK, ID_IMAGE_LOCK)).build()
                ).build();
    }

    /**
     * Creates a clickable button with an image
     * @param clickId the id of the button, for reference when it gets clicked
     * @param imageId the image to show on the button
     * @return
     */
    private LayoutElementBuilders.LayoutElement buttonWithImage(String clickId, String imageId) {
        return new LayoutElementBuilders.Box.Builder()
                .setWidth(dp(48f))
                .setHeight(dp(48f))
                .setModifiers(
                        new ModifiersBuilders.Modifiers.Builder()
                                .setBackground(
                                        new ModifiersBuilders.Background.Builder()
                                                .setColor(
                                                        ColorBuilders.argb(ContextCompat.getColor(this, R.color.roundbuttonWhite))
                                                )
                                                .setCorner(
                                                        new ModifiersBuilders.Corner.Builder().setRadius(dp(48f / 2)).build()
                                                )
                                                .build()
                                )
                                .setSemantics(
                                        new ModifiersBuilders.Semantics.Builder()
                                                .setContentDescription("Action Button")
                                                .build()
                                )
                                .setClickable(
                                        new ModifiersBuilders.Clickable.Builder()
                                                .setId(clickId)
                                                .setOnClick(new ActionBuilders.LoadAction.Builder().build())
                                                .build()
                                )
                                .build()
                )
                .addContent(
                        new LayoutElementBuilders.Image.Builder()
                                .setWidth(dp(24f))
                                .setHeight(dp(24f))
                                .setResourceId(imageId)
                                .build()
                )
                .build();
    }
}
