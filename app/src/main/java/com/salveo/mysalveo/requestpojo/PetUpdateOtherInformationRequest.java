package com.salveo.mysalveo.requestpojo;

public class PetUpdateOtherInformationRequest {


    /**
     * _id : 603e098e2c2b43125f8cb7f8
     * pet_spayed : false
     * pet_purebred : true
     * pet_frnd_with_dog : false
     * pet_frnd_with_cat : false
     * pet_frnd_with_kit : false
     * pet_microchipped : false
     * pet_tick_free : false
     * pet_private_part : false
     */

    private String _id;
    private boolean pet_spayed;
    private boolean pet_purebred;
    private boolean pet_frnd_with_dog;
    private boolean pet_frnd_with_cat;
    private boolean pet_frnd_with_kit;
    private boolean pet_microchipped;
    private boolean pet_tick_free;
    private boolean pet_private_part;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;

    }


    public boolean isPet_spayed() {
        return pet_spayed;
    }

    public void setPet_spayed(boolean pet_spayed) {
        this.pet_spayed = pet_spayed;

    }


    public boolean isPet_purebred() {
        return pet_purebred;
    }

    public void setPet_purebred(boolean pet_purebred) {
        this.pet_purebred = pet_purebred;

    }


    public boolean isPet_frnd_with_dog() {
        return pet_frnd_with_dog;
    }

    public void setPet_frnd_with_dog(boolean pet_frnd_with_dog) {
        this.pet_frnd_with_dog = pet_frnd_with_dog;

    }

    public boolean isPet_frnd_with_cat() {
        return pet_frnd_with_cat;
    }

    public void setPet_frnd_with_cat(boolean pet_frnd_with_cat) {
        this.pet_frnd_with_cat = pet_frnd_with_cat;

    }

    public boolean isPet_frnd_with_kit() {
        return pet_frnd_with_kit;
    }

    public void setPet_frnd_with_kit(boolean pet_frnd_with_kit) {
        this.pet_frnd_with_kit = pet_frnd_with_kit;

    }


    public boolean isPet_microchipped() {
        return pet_microchipped;
    }

    public void setPet_microchipped(boolean pet_microchipped) {
        this.pet_microchipped = pet_microchipped;
    }


    public boolean isPet_tick_free() {
        return pet_tick_free;
    }

    public void setPet_tick_free(boolean pet_tick_free) {
        this.pet_tick_free = pet_tick_free;

    }


    public boolean isPet_private_part() {
        return pet_private_part;
    }

    public void setPet_private_part(boolean pet_private_part) {
        this.pet_private_part = pet_private_part;

    }
}
