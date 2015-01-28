package stealth.needle.starrealmscounter;

import stealth.needle.starrealmscounter.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = false;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

//        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);

        // Don't show action bar, ever
        this.getActionBar().hide();

        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            // If the ViewPropertyAnimator API is available
                            // (Honeycomb MR2 and later), use it to animate the
                            // in-layout UI controls at the bottom of the
                            // screen.
                            if (mControlsHeight == 0) { }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                        } else {
                        }

                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });

        // Set up the user interaction to manually show or hide the system UI.
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TOGGLE_ON_CLICK) {
                    mSystemUiHider.toggle();
                } else {
                    mSystemUiHider.show();
                }
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.

        /* Influence */

        /* Player 1, plus HP */
        findViewById(R.id.player1_health_plus10).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player1_health_plus10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_health, 10, true);
            }
        });
        findViewById(R.id.player1_health_plus5).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player1_health_plus5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_health, 5, true);
            }
        });
        findViewById(R.id.player1_health_plus1).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player1_health_plus1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_health, 1, true);
            }
        });

        /* Player 1, minus HP */
        findViewById(R.id.player1_health_minus10).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player1_health_minus10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_health, 10, false);
            }
        });
        findViewById(R.id.player1_health_minus5).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player1_health_minus5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_health, 5, false);
            }
        });
        findViewById(R.id.player1_health_minus1).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player1_health_minus1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_health, 1, false);
            }
        });

        /* Player 2, plus HP */
        findViewById(R.id.player2_health_plus10).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player2_health_plus10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_health, 10, true);
            }
        });
        findViewById(R.id.player2_health_plus5).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player2_health_plus5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_health, 5, true);
            }
        });
        findViewById(R.id.player2_health_plus1).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player2_health_plus1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_health, 1, true);
            }
        });

        /* Player 2, minus HP */
        findViewById(R.id.player2_health_minus10).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player2_health_minus10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_health, 10, false);
            }
        });
        findViewById(R.id.player2_health_minus5).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player2_health_minus5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_health, 5, false);
            }
        });
        findViewById(R.id.player2_health_minus1).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player2_health_minus1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_health, 1, false);
            }
        });

        /* Commerce */

        /* Player 1 */
        findViewById(R.id.player1_commerce_plus1).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player1_commerce_plus1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_commerce, 1, true);
            }
        });
        findViewById(R.id.player1_commerce_plus2).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player1_commerce_plus2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_commerce, 2, true);
            }
        });
        findViewById(R.id.player1_commerce_plus4).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player1_commerce_plus4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_commerce, 4, true);
            }
        });
        findViewById(R.id.player1_commerce_plus8).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player1_commerce_plus8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_commerce, 8, true);
            }
        });
        findViewById(R.id.player1_commerce_clear).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player1_commerce_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAmount(R.id.player1_commerce);
            }
        });

        /* Player 2 */
        findViewById(R.id.player2_commerce_plus1).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player2_commerce_plus1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_commerce, 1, true);
            }
        });
        findViewById(R.id.player2_commerce_plus2).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player2_commerce_plus2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_commerce, 2, true);
            }
        });
        findViewById(R.id.player2_commerce_plus4).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player2_commerce_plus4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_commerce, 4, true);
            }
        });
        findViewById(R.id.player2_commerce_plus8).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player2_commerce_plus8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_commerce, 8, true);
            }
        });
        findViewById(R.id.player2_commerce_clear).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player2_commerce_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAmount(R.id.player2_commerce);
            }
        });

        /* Attack */

        /* Player 1 */
        findViewById(R.id.player1_attack_plus1).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player1_attack_plus1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_attack, 1, true);
            }
        });
        findViewById(R.id.player1_attack_plus2).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player1_attack_plus2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_attack, 2, true);
            }
        });
        findViewById(R.id.player1_attack_plus4).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player1_attack_plus4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_attack, 4, true);
            }
        });
        findViewById(R.id.player1_attack_plus8).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player1_attack_plus8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_attack, 8, true);
            }
        });
        findViewById(R.id.player1_attack_clear).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player1_attack_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAmount(R.id.player1_attack);
            }
        });

        findViewById(R.id.player1_attack_apply).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player1_attack_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyAttack(R.id.player1_attack, R.id.player2_health);
            }
        });

        /* Player 2 */
        findViewById(R.id.player2_attack_plus1).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player2_attack_plus1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_attack, 1, true);
            }
        });
        findViewById(R.id.player2_attack_plus2).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player2_attack_plus2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_attack, 2, true);
            }
        });
        findViewById(R.id.player2_attack_plus4).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player2_attack_plus4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_attack, 4, true);
            }
        });
        findViewById(R.id.player2_attack_plus8).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player2_attack_plus8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_attack, 8, true);
            }
        });
        findViewById(R.id.player2_attack_clear).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player2_attack_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAmount(R.id.player2_attack);
            }
        });

        findViewById(R.id.player2_attack_apply).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.player2_attack_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyAttack(R.id.player2_attack, R.id.player1_health);
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    /* UI logic */

    /**
     * Update displayed amount for specific view (influence, commerce, etc)
     * @param playerView TextView to update
     * @param amount Number to add/subtract
     * @param increment Should add or subtract
     */
    public void updateAmount(int playerView, int amount, boolean increment)  {
        TextView amountView = (TextView)findViewById(playerView);
        Integer currentAmount = Integer.parseInt(amountView.getText().toString());
        Integer updatedAmount = (increment) ? currentAmount + amount : currentAmount - amount;
        amountView.setText(String.valueOf(updatedAmount));
    }

    /**
     * Reset specified view
     * @param playerView TextView to reset
     */
    public void clearAmount(int playerView) {
        ((TextView)findViewById(playerView)).setText("0");
    }

    /**
     * Apply one player's attack to other's influence
     * @param attackField First player attack field
     * @param defenderInfluence Second player influence field
     */
    public void applyAttack(int attackField, int defenderInfluence) {
        TextView attack = (TextView)findViewById(attackField);
        TextView influence = (TextView)findViewById(defenderInfluence);
        int resultingInfluence = Integer.valueOf(influence.getText().toString()) -
                Integer.valueOf(attack.getText().toString());

        influence.setText(String.valueOf(resultingInfluence));
    }

    
}
